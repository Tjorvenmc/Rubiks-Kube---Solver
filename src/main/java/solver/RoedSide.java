package solver;
public class RoedSide extends Side{

    RoedSide(Kube kube){
        super(kube);
    }

    @Override
    void vriMed(){

        String temp = kuben.hentRute(27);
        kuben.settRute(27, kuben.hentRute(33));
        kuben.settRute(33, kuben.hentRute(35));
        kuben.settRute(35, kuben.hentRute(29));
        kuben.settRute(29, temp);

        String temp2 = kuben.hentRute(28);
        kuben.settRute(28, kuben.hentRute(30));
        kuben.settRute(30, kuben.hentRute(34));
        kuben.settRute(34, kuben.hentRute(32));
        kuben.settRute(32, temp2);

        String temp3 = kuben.hentRute(2);
        String temp4 = kuben.hentRute(5);
        String temp5 = kuben.hentRute(8);

        kuben.settRute(2, kuben.hentRute(20));
        kuben.settRute(5, kuben.hentRute(23));
        kuben.settRute(8, kuben.hentRute(26));

        kuben.settRute(20, kuben.hentRute(47));
        kuben.settRute(23, kuben.hentRute(50));
        kuben.settRute(26, kuben.hentRute(53));

        kuben.settRute(47, kuben.hentRute(42));
        kuben.settRute(50, kuben.hentRute(39));
        kuben.settRute(53, kuben.hentRute(36));

        kuben.settRute(42, temp3);
        kuben.settRute(39, temp4);
        kuben.settRute(36, temp5);
    }

    @Override
    void vriMot(){

        String temp = kuben.hentRute(27);
        kuben.settRute(27, kuben.hentRute(29));
        kuben.settRute(29, kuben.hentRute(35));
        kuben.settRute(35, kuben.hentRute(33));
        kuben.settRute(33, temp);

        String temp2 = kuben.hentRute(28);
        kuben.settRute(28, kuben.hentRute(32));
        kuben.settRute(32, kuben.hentRute(34));
        kuben.settRute(34, kuben.hentRute(30));
        kuben.settRute(30, temp2);

        String temp3 = kuben.hentRute(2);
        String temp4 = kuben.hentRute(5);
        String temp5 = kuben.hentRute(8);

        kuben.settRute(2, kuben.hentRute(42));
        kuben.settRute(5, kuben.hentRute(39));
        kuben.settRute(8, kuben.hentRute(36));

        kuben.settRute(42, kuben.hentRute(47));
        kuben.settRute(39, kuben.hentRute(50));
        kuben.settRute(36, kuben.hentRute(53));

        kuben.settRute(47, kuben.hentRute(20));
        kuben.settRute(50, kuben.hentRute(23));
        kuben.settRute(53, kuben.hentRute(26));

        kuben.settRute(26, temp5);
        kuben.settRute(23, temp4);
        kuben.settRute(20, temp3);
    }
}
