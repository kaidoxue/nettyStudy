package study.netty.utils;

public class StringReverseBySingleDot {

    public static final String reverse(String oriStr){
        String retStr=oriStr;

        String[] sArray = oriStr.split("\\.");
        if(sArray.length>=2){
            StringBuilder keyBuf = new StringBuilder(sArray[sArray.length-1]);
            StringBuilder buf = new StringBuilder(64);
            for(int i=0;i<sArray.length;i++){
                if(i==sArray.length-1){
                    buf.append(keyBuf.reverse().toString());
                }else{
                    buf.append(sArray[i]).append(".");
                }
            }
            retStr = buf.toString();
        }

        return retStr;
    }
}
