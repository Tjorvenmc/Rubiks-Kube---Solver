package solver;
public class GulSide extends Side{

    GulSide(Kube kube){
        super(kube);
    }

    @Override
    void vriMed(){

        String temp = kuben.hentRute(36);
        kuben.settRute(36, kuben.hentRute(42));
        kuben.settRute(42, kuben.hentRute(44));
        kuben.settRute(44, kuben.hentRute(38));
        kuben.settRute(38, temp);

        String temp2 = kuben.hentRute(37);
        kuben.settRute(37, kuben.hentRute(39));
        kuben.settRute(39, kuben.hentRute(43));
        kuben.settRute(43, kuben.hentRute(41));
        kuben.settRute(41, temp2);

        String temp3 = kuben.hentRute(53);
        String temp4 = kuben.hentRute(52);
        String temp5 = kuben.hentRute(51);

        kuben.settRute(53, kuben.hentRute(15));
        kuben.settRute(52, kuben.hentRute(12));
        kuben.settRute(51, kuben.hentRute(9));

        kuben.settRute(15, kuben.hentRute(0));
        kuben.settRute(12, kuben.hentRute(1));
        kuben.settRute(9, kuben.hentRute(2));

        kuben.settRute(0, kuben.hentRute(29));
        kuben.settRute(1, kuben.hentRute(32));
        kuben.settRute(2, kuben.hentRute(35));

        kuben.settRute(29, temp3);
        kuben.settRute(32, temp4);
        kuben.settRute(35, temp5);

    }

    @Override
    void vriMot(){

        String temp = kuben.hentRute(36);
        kuben.settRute(36, kuben.hentRute(38));
        kuben.settRute(38, kuben.hentRute(44));
        kuben.settRute(44, kuben.hentRute(42));
        kuben.settRute(42, temp);

        String temp2 = kuben.hentRute(37);
        kuben.settRute(37, kuben.hentRute(41));
        kuben.settRute(41, kuben.hentRute(43));
        kuben.settRute(43, kuben.hentRute(39));
        kuben.settRute(39, temp2);

        String temp3 = kuben.hentRute(53);
        String temp4 = kuben.hentRute(52);
        String temp5 = kuben.hentRute(51);

        kuben.settRute(53, kuben.hentRute(29));
        kuben.settRute(52, kuben.hentRute(32));
        kuben.settRute(51, kuben.hentRute(35));

        kuben.settRute(29, kuben.hentRute(0));
        kuben.settRute(32, kuben.hentRute(1));
        kuben.settRute(35, kuben.hentRute(2));

        kuben.settRute(0, kuben.hentRute(15));
        kuben.settRute(1, kuben.hentRute(12));
        kuben.settRute(2, kuben.hentRute(9));

        kuben.settRute(15, temp3);
        kuben.settRute(12, temp4);
        kuben.settRute(9, temp5);
    }
}
