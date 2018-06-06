package lee.utils;

import org.dom4j.*;

import java.util.*;

/**
 * @author leon
 * @date 2018-06-01 15:10
 * @desc 数据处理类
 */
public class DataHelper {
    /**
     *  排序过滤拼接字符串
     * @param params 注满参数的Map
     * @param filterKey 你想要过滤的字符串
     * @return
     */
    public static String GetSortFilterQueryString(Map<String, String> params, String[] filterKey)
    {
        List<Map.Entry<String, String>> keyValues = new ArrayList<>(params.entrySet());

        Collections.sort(keyValues, Comparator.comparing(o -> (o.getKey())));
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<keyValues.size();i++) {
            boolean filter=false;
            if(filterKey!=null&&filterKey.length>0)
            {
                for(int index=0;index<filterKey.length;index++)
                {
                    if(filterKey[index].equalsIgnoreCase(keyValues.get(i).getKey()))
                    {
                        filter=true;
                        break;
                    }
                }
            }
            //过滤的KEY不参与
            if(filter) continue;
            sb.append(keyValues.get(i).getKey()+ "=" + keyValues.get(i).getValue());
            sb.append("&");
        }
        return sb.substring(0, sb.length()-1);
    }

    /**
     * 不排序,直接拼接请求字符串
     * @param params 注满参数的map
     * @return
     */
    public static String GetQueryString(Map<String, String> params)
    {
        Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            sb.append(key + "=" +val).append("&");
        }
        if(sb.length()==0) return "";
        return sb.substring(0, sb.length()-1);
    }

    /**
     * 排序过后拼接字符串并转化为小写(按照ASCII码排序) 例子 a=1&b=c&bc=ab&c=5
     * @param params
     * @return
     */
    public static String GetSortQueryToLowerString(Map<String, String> params)
    {
        List<Map.Entry<String, String>> keyValues = new ArrayList<>(params.entrySet());

        Collections.sort(keyValues, Comparator.comparing(o -> (o.getKey())));

        StringBuilder sb = new StringBuilder();
        for (int i=0;i<keyValues.size();i++) {
            if(keyValues.get(i).getValue()==null)
            {
                sb.append(keyValues.get(i).getKey()+ "= " );
            }
            else
            {
                sb.append(keyValues.get(i).getKey()+ "=" + keyValues.get(i).getValue().toLowerCase());
            }
            sb.append("&");
        }

        return sb.substring(0, sb.length()-1);

    }

    /**
     * 排序过后拼接字符串(按照ASCII码排序) 例子:A=1&b=c&bc=ab&c=5
     * @param params 注满参数的map
     * @return
     */
    public static String GetSortQueryString(Map<String, String> params)
    {
        List<Map.Entry<String, String>> keyValues =
                new ArrayList<>(params.entrySet());

        Collections.sort(keyValues, Comparator.comparing(o-> (o.getKey())));

        StringBuilder sb = new StringBuilder();
        for (int i=0;i<keyValues.size();i++) {
            sb.append(keyValues.get(i).getKey()+ "=" + keyValues.get(i).getValue());
            sb.append("&");
        }

        return sb.substring(0, sb.length()-1);

    }
    /**
     * 将XML解析成MAP
     * @author Leo
     * @version 0.1
     */
    public static Map<String, String> getXML(String requestXml){
        Map<String, String> map = new HashMap();
        // 将字符串转为XML
        Document doc;
        try {
            doc = DocumentHelper.parseText(requestXml);
            // 获取根节点
            Element rootElm = doc.getRootElement();//从root根节点获取请求报文
            map = parseXML(rootElm, new HashMap());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }
    /**
     * 将xml解析成map键值对
     * <功能详细描述>
     * @param ele 需要解析的xml对象
     * @param map 入参为空，用于内部迭代循环使用
     * @return map键值对
     * @see
     */
    public static Map<String, String> parseXML(Element ele, Map<String, String> map)
    {
        for (Iterator<?> i = ele.elementIterator(); i.hasNext();)
        {
            Element node = (Element)i.next();
            //System.out.println("parseXML node name:" + node.getName());
            if (node.attributes() != null && node.attributes().size() > 0)
            {
                for (Iterator<?> j = node.attributeIterator(); j.hasNext();)
                {
                    Attribute item = (Attribute)j.next();
                    map.put(item.getName(), item.getValue());
                }
            }
            if (node.getText().length() > 0)
            {
                map.put(node.getName(), node.getText());
            }
            if (node.elementIterator().hasNext())
            {
                parseXML(node, map);
            }
        }
        return map;
    }
    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> URLRequestParams(String URL)
    {
        Map<String, String> mapRequest = new HashMap<>();
        String[] arrSplit;
        String strUrlParam=URL;
        if(strUrlParam==null)
        {
            return mapRequest;
        }
        strUrlParam=URL.indexOf("?")>0?URL.substring(URL.indexOf("[?]")+1) :URL ;
        //解决返回值中带有URL含参数“?”导致破坏键值对 返回的URL后的参数 需要重新组合
        //例如：key1=value1&key2=value2&key3=url?a=a1&b=b1&key4=value4
        //分别获取后的键值为 key1,key2,key3,akey,bkey,key4
        //如需获取key3 的原始值 key3+"?"+akey+bkey
        strUrlParam=strUrlParam.replaceAll("[?]", "&");
        //每个键值为一组
        arrSplit=strUrlParam.split("[&]");
        for(String strSplit:arrSplit)
        {
            String[] arrSplitEqual;
            arrSplitEqual= strSplit.split("[=]");
            //解析出键值
            if(arrSplitEqual.length>1)
            {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            }
            else
            {
                if(arrSplitEqual[0]!="")
                {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }
}
