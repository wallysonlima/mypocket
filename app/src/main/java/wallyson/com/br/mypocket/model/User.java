package wallyson.com.br.mypocket.model;

/**
 * Created by wally on 28/06/16.
 */
public class User {
    private String name, email;
    private int codUser;

    public User(String name, String email, int codUser) {
        this.name = name;
        this.email = email;
        this.codUser = codUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCodUser() {
        return codUser;
    }

}
