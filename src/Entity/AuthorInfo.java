package Entity;

public class AuthorInfo {
  private int AuthorID;
  private String AuthorName;

  public AuthorInfo() {
    
  }

  public AuthorInfo(int AuthorID, String AuthorName){
    this.AuthorID = AuthorID;
    this.AuthorName = AuthorName;
  }

  public int getAuthorID () {
    return AuthorID;
  }
  public void setAuthorID(int AuthorID) {
    this.AuthorID = AuthorID;
  }

  public String getAuthorName () {
    return AuthorName;
  }
  public void setAuthorName(String AuthorName) {
    this.AuthorName = AuthorName;
  }

  public String toString() {
    return "AuthorInfo{" +
                "AuthorID=" + AuthorID +
                ", AuthorName='" + AuthorName + '}';
    }
  }