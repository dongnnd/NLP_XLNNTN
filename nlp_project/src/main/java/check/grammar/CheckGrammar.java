package check.grammar;

/**
 * Created by DongND on 10/30/2016.
 */
public class CheckGrammar {

    public Token token;
    public Data data;

    public CheckGrammar(Token token){
        this.token=token;
    }

    // kiểm tra phụ âm đầu
    public boolean checkPAM(){
        String phuamdau=token.phu_am_dau;
        for(int i=0;i<data.phuamdau.length;i++){
            if(phuamdau.equals(data.phuamdau[i])){
                return true;
            }
        }
        return false;
    }

    // kiểm tra vần
    public boolean checkVan(){
        String van=token.van;
        for(int i=0;i<data.bangvan.length;i++){
            for(int j=0;j<data.bangvan[i].length;j++){
                if(van.equals(data.bangvan[i][j])){
                    return true;
                }
            }
        }
        return false;
    }

    // kiểm tra phụ âm cuối
    public boolean checkPAC(){
        String phuamcuoi=token.phu_am_cuoi;
        for(int i=0;i<data.phuamcuoi.length;i++){
            if(phuamcuoi.equals(data.phuamcuoi[i])){
                return true;
            }
        }
        return false;
    }

    public boolean checkDau(){
        String am=token.th_am;
        String phuamcuoi=token.phu_am_cuoi;
        int dau=token.vi_tri_dau;
        String am_kd=getVanKhongDau(token.th_am);
        if(dau==-1){
            return true;
        }else{
            if(am.length()==1&&dau==0){
                return true;
            }else if(am.length()==2&&phuamcuoi!=""&&dau==1){
                return true;
            }else if((!am_kd.equals("oa")&&!am_kd.equals("oe")&&!am_kd.equals("uê")&&!am_kd.equals("uy"))&&am_kd.length()==2&&dau==0){
                return true;
            }else if((am_kd.equals("oa")||am_kd.equals("oe")||am_kd.equals("uê")||am_kd.equals("uy"))
                    &&dau==1){
                return true;
            }else if(am.length()==3&&phuamcuoi!=""&&dau==2){
                return true;
            }else if(am.length()==3&&phuamcuoi==""&&dau==1){
                return true;
            }
        }

        return false;
    }

    public boolean checkPAD_AM(){
        String[] data1={"i", "y", "e", "ê"};
        String[] data2={"i", "e", "ê"};
        String phuamdau=token.phu_am_dau;
        String van=token.van;
        String s1=String.valueOf(van.charAt(0));
        if(phuamdau.equals("k")||phuamdau.equals("ng")||phuamdau.equals("ngh")){
            for(int i=0;i<data1.length;i++){
                if(s1.equals(data1[i])){
                    return true;
                }
            }
        }

        return false;
    }

    // k, ng, ngh phair đi với các nguyên âm : i, y, ê, e
    public boolean checkPAD_AM_1(String phuamdau, String nguyeamdau, String[] data){
        if(phuamdau.equals("k")||phuamdau.equals("ng")||phuamdau.equals("ngh")){
            for(int i=0;i<data.length;i++){
                if(nguyeamdau.equals(data[i])){
                    return true;
                }
            }
        }
        return false;
    }

    // âm tiết nào mà chỉ có 1 nguyên âm thì dấu được đặt ở nguyên âm đó
    public boolean checkDau_1(String am, int dau) {
        if(am.length()==1&& dau==0){
            return true;
        }
        return false;
    }

    // âm tiết nào có 2 nguyên âm, tận cùng là 1 phụ âm thì dấu được đặt vào nguyên âm gần với phụ âm hơn


    // chuyển âm có dấu thành âm không dấu
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

}
