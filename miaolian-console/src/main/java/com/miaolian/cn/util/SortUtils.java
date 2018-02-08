package com.miaolian.cn.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by crow on 2016/3/25 0025.
 * 排序工具类
 */
public class SortUtils<E> {
    /**
     * 对列表中的数据按指定字段进行排序。要求类必须有相关的方法返回字符串、整型、日期等值以进行比较。
     */
    public void sortByMethod(List<E> list, final String method,
                             final boolean reverseFlag) {
        Collections.sort(list, new Comparator<Object>() {
            @SuppressWarnings("unchecked")
            public int compare(Object arg1, Object arg2) {
                int result=0;
                try {
                    Method m1 = ((E) arg1).getClass().getMethod(method, null);
                    Method m2 = ((E) arg2).getClass().getMethod(method, null);
                    Object obj1 = m1.invoke(((E)arg1), null);
                    Object obj2 = m2.invoke(((E)arg2), null);
                    if(obj1 instanceof String) {
                        // 字符串
                        result = obj1.toString().compareTo(obj2.toString());
                    }else if(obj1 instanceof Date) {
                        // 日期
                        long l = ((Date)obj1).getTime() - ((Date)obj2).getTime();
                        if(l > 0) {
                            result = 1;
                        }else if(l < 0) {
                            result = -1;
                        }else {
                            result = 0;
                        }
                    } else if(obj1 instanceof Double) {
                        double num = ((Math.round((Double) obj1*100)/100.0) - (Math.round((Double) obj2*100)/100.0));
                        if(num>0){
                            result = 1;
                        }else if(num==0){
                            result = 0;
                        }else{
                            result = -1;
                        }
                    }else {
                        // 目前尚不支持的对象，直接转换为String，然后比较，后果未知
                        result = obj1.toString().compareTo(obj2.toString());
                    }
                    if (reverseFlag) {
                        // 倒序
                        result = -result;
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException nsme) {
                    nsme.printStackTrace();
                }
                return result;
            }
        });
    }

    //字母Z使用了两个标签，这里有２７个值
    //i, u, v都不做声母, 跟随前面的字母
    private char[] chartable =
            {
                   /* '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈',
                    '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然',
                    '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', '座'*/
            };
    private char[] alphatableb =
            {
                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                    'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
            };
    private char[] alphatables =
            {
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                    'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
            };
    private int[] table = new int[27];  //初始化
    {
        for (int i = 0; i < 27; ++i) {
            table[i] = gbValue(chartable[i]);
        }
    }
    //主函数,输入字符,得到他的声母,
    //英文字母返回对应的大小写字母
    //其他非简体汉字返回 '0'  按参数
    public char Char2Alpha(char ch,String type) {
        if (ch >= 'a' && ch <= 'z')
            return (char) (ch - 'a' + 'A');//为了按字母排序先返回大写字母
        // return ch;
        if (ch >= 'A' && ch <= 'Z')
            return ch;

        int gb = gbValue(ch);
        if (gb < table[0])
            return '0';

        int i;
        for (i = 0; i < 26; ++i) {
            if (match(i, gb))
                break;
        }

        if (i >= 26){
            return '0';}
        else{
            if("b".equals(type)){//大写
                return alphatableb[i];
            }else{//小写
                return alphatables[i];
            }
        }
    }
    //根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串
    public String String2Alpha(String SourceStr,String type) {
        String Result = "";
        int StrLength = SourceStr.length();
        int i;
        try {
            for (i = 0; i < StrLength; i++) {
                Result += Char2Alpha(SourceStr.charAt(i),type);
            }
        } catch (Exception e) {
            Result = "";
        }
        return Result;
    }
    //根据一个包含汉字的字符串返回第一个汉字拼音首字母的字符串
    public String String2AlphaFirst(String SourceStr,String type) {
        String Result = "";
        try {
            Result += Char2Alpha(SourceStr.charAt(0),type);
        } catch (Exception e) {
            Result = "";
        }
        return Result;
    }
    private boolean match(int i, int gb) {
        if (gb < table[i])
            return false;
        int j = i + 1;

        //字母Z使用了两个标签
        while (j < 26 && (table[j] == table[i]))
            ++j;
        if (j == 26)
            return gb <= table[j];
        else
            return gb < table[j];
    }

    //取出汉字的编码
    private int gbValue(char ch) {
        String str="";
        str += ch;
        try {
            byte[] bytes = str.getBytes("GBK");
            if (bytes.length < 2)
                return 0;
            return (bytes[0] << 8 & 0xff00) + (bytes[1] &
                    0xff);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 根据对象的某一个字段排序
     */
    public Map sort(List<E> list,String sortName){
        Map map=new HashMap();
        ArrayList arraylist=new ArrayList();
        String[] alphatableb =
                {
                        "A", "B", "C", "D", "E", "F", "G", "H", "I",
                        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
                };
        for(String a:alphatableb){
            for(E o:list){//为了排序都返回大写字母
                try {
                    Field f = o.getClass().getDeclaredField(sortName);
                    if(a.equals(String2AlphaFirst(f.get(o).toString(),"b"))){
                        arraylist.add(o);
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            map.put(a,arraylist);
            arraylist=new ArrayList();
        }
        return map;
    }

    /**
     * 根据单个字段排序
     */
    public Map sort(List list){
        Map map=new HashMap();
        ArrayList arraylist=new ArrayList();
        String[] alphatableb =
                {
                        "A", "B", "C", "D", "E", "F", "G", "H", "I",
                        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
                };
        for(String a:alphatableb){
            for(int i=0;i<list.size();i++){//为了排序都返回大写字母
                if(a.equals(String2AlphaFirst(list.get(i).toString(),"b"))){
                    arraylist.add(list.get(i).toString());
                }
            }
            map.put(a,arraylist);
            arraylist=new ArrayList();
        }
        return map;
    }

   /* // 测试函数
    public static void main(String[] args) throws Exception {
        // 生成自定义对象，然后对它按照指定字段排序
        List<User> listMember = new ArrayList<>();
        listMember.add(new User(2, "5",new Date()));
        listMember.add(new User(1, "3", new Date()));
        listMember.add(new User(3, "4", new Date()));
        System.out.println("Member当前顺序...");
        System.out.println(listMember.toString());

        // 方式二排序输出
        SortUtils<User> msList = new SortUtils<>();
        msList.sortByMethod(listMember, "getId", false);
        System.out.println("Member按字段用户Id排序后...");
        System.out.println(listMember.toString());

        msList.sortByMethod(listMember, "getUserName", false);
        System.out.println("Member按字段用户排序后...");
        System.out.println(listMember.toString());

        msList.sortByMethod(listMember, "getCreateTime", true);
        System.out.println("Member按字段创建日期倒序后...");
        System.out.println(listMember.toString());
    }*/
   /*public static void main(String[] args) {
       SortUtils obj1 = new SortUtils();
       System.out.println("======================");
       ArrayList list=new ArrayList();
       list.add("adisen");
       list.add("bulsi");
       list.add("Kobe");
       list.add("布丁");
       list.add("杜甫");
       list.add("元方");
       Map map=obj1.sort(list);
       System.out.println("-------分组后的输出-----------");
       System.out.println(map.get("A"));
       System.out.println(map.get("B"));
       System.out.println(map.get("C"));
       System.out.println(map.get("D"));
       System.out.println(map.get("Y"));
   }*/
}
