package solver;
public abstract class Side {
    Kube kuben = null;

    Side(Kube kube){
        kuben = kube;
    }

    abstract void vriMed();

    abstract void vriMot();
}
