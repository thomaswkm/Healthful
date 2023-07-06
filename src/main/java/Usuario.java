
public class Usuario {
    private String rut;
    private String password;

    public Usuario(String rut, String password) {
        this.rut = rut;
        this.password = password;
    }

    public Usuario() {

    }

    public boolean verificacion(String rut, String password){
        if(this.rut.equals(rut)&&this.password.equals(password)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return rut+","+password;
    }
}