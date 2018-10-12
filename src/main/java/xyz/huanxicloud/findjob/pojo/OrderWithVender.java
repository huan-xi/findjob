package xyz.huanxicloud.findjob.pojo;

public class OrderWithVender {
  private POrder pOrder;
  private Vender vender;
  private Position position;

    public POrder getpOrder() {
        return pOrder;
    }

    public void setpOrder(POrder pOrder) {
        this.pOrder = pOrder;
    }

    public Vender getVender() {
        return vender;
    }

    public void setVender(Vender vender) {
        this.vender = vender;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
