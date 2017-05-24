package check.grammar;

/**
 * Created by DongND on 10/30/2016.
 */
public class Token {
    public String phu_am_dau;
    public String van;
    public String th_am;
    public String phu_am_cuoi;
    public int vi_tri_dau;

    public Token(String phu_am_dau, String van, String th_am, String phu_am_cuoi, int vi_tri_dau){
        this.phu_am_dau=phu_am_dau;
        this.van=van;
        this.th_am=th_am;
        this.phu_am_cuoi=phu_am_cuoi;
        this.vi_tri_dau=vi_tri_dau;
    }

    public String getPhu_am_dau() {
        return phu_am_dau;
    }

    public void setPhu_am_dau(String phu_am_dau) {
        this.phu_am_dau = phu_am_dau;
    }

    public String getVan() {
        return van;
    }

    public void setVan(String van) {
        this.van = van;
    }

    public String getTh_am() {
        return th_am;
    }

    public void setTh_am(String th_am) {
        this.th_am = th_am;
    }

    public String getPhu_am_cuoi() {
        return phu_am_cuoi;
    }

    public void setPhu_am_cuoi(String phu_am_cuoi) {
        this.phu_am_cuoi = phu_am_cuoi;
    }

    public int getVi_tri_dau() {
        return vi_tri_dau;
    }

    public void setVi_tri_dau(int vi_tri_dau) {
        this.vi_tri_dau = vi_tri_dau;
    }


}
