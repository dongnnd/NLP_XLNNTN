package check.grammar;

/**
 * Created by DongND on 10/30/2016.
 */

public class GetToken {

    Data data=new Data();

    public String phu_am_dau;
    public String van;
    public String th_am;
    public String phu_am_cuoi;
    public String words;
    int vi_tri_dau;

    public GetToken(String words){
        this.words=words;
    }

    // tách lấy phụ âm đầu và vần
    public void getPhuAmDauVan(String words){
        int i=getIndexNA(words);
        if(i==-1){
            phu_am_dau="";
            van=words;
        }else{
            String s1=words.substring(0, i);
            String s2=words.substring(i, i+1);
            if((s1.equals("q")&&s2.equals("u"))||(s1.equals("g")&&s2.equals("i"))){
                phu_am_dau=words.substring(0, i+1);
                van=words.substring(i+1, words.length());
            }else{
                phu_am_dau=words.substring(0, i);
                van=words.substring(i, words.length());
            }

        }
    }


    // tách lấy tổ hợp âm và phụ âm cuối
    public void getVanPhuAmCuoi(String van){
        int index=getIndexPA(van);
        if(index==-1){
            th_am=van;
            phu_am_cuoi="";
        }else{
            th_am=van.substring(0, index);
            phu_am_cuoi=van.substring(index, van.length());
        }
    }

    // tách vần ban đầu thành vần không dấu
    public String getVanKhongDau(String van){
        String vankhongdau=van;
        for(int i=0;i<van.length();i++){
            for(int j=0;j<data.arr_nguyenam.length;j++){
                for(int k=1;k<data.arr_nguyenam[j].length;k++){
                    if(String.valueOf(van.charAt(i)).equals(data.arr_nguyenam[j][k])){
                        vankhongdau=van.replace(String.valueOf(van.charAt(i)), data.arr_nguyenam[j][0]);
                    }
                }
            }
        }
        return vankhongdau;
    }


    // tách lấy vị trí của dấu trong tổ hợp âm
    public int getDauAm(String am){
        for(int i=0;i<am.length();i++){
            for(int j=0;j<data.nguyenamDau.length;j++){

                if(String.valueOf(am.charAt(i)).equals(data.nguyenamDau[j])){
                    return i;
                }

            }
        }
        return -1;
    }

    // lấy vị trí của nguyên âm đầu tiên
    public int getIndexNA(String words){
        int position=-1;
        for(int i=0;i<words.length();i++){
            for(int j=0;j<data.arr_nguyenam.length;j++){
                for(int k=0;k<data.arr_nguyenam[j].length;k++){
                    if(String.valueOf(words.charAt(i)).equals(data.arr_nguyenam[j][k])){
                        return i;
                    }
                }
            }
        }

        return position;
    }



    // lấy vị trí của phụ âm
    public int getIndexPA(String van){
        int index=-1;
        for(int i=0;i<data.phuamcuoi.length;i++){
            index=van.indexOf(data.phuamcuoi[i]);
            if(index!=-1){
                return index;
            }
        }
        return index;
    }

}
