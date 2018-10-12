package xyz.huanxicloud.findjob.pojo;

public class OrderWithUser {
  private POrder pOrder;
  private User user;
  private Position position;

    public POrder getpOrder() {
        return pOrder;
    }

    public void setpOrder(POrder pOrder) {
        this.pOrder = pOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
