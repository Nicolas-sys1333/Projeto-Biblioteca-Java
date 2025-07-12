package  com.biblioteca.model.entity;

public abstract class AbstractEntity {
  private int id;
  private int aluguel;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getID(){
    return aluguel;
  }

  public void setID(int aluguel){
    this.aluguel = aluguel;
  }

}
