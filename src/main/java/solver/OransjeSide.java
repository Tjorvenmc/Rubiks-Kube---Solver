package solver;
public class OransjeSide extends Side{

    OransjeSide(Kube kube){
        super(kube);
    }

    @Override
    void vriMed(){

        String temp = kuben.hentRute(9);
        kuben.settRute(9, kuben.hentRute(15));
        kuben.settRute(15, kuben.hentRute(17));
        kuben.settRute(17, kuben.hentRute(11));
        kuben.settRute(11, temp);

        String temp2 = kuben.hentRute(10);
        kuben.settRute(10, kuben.hentRute(12));
        kuben.settRute(12, kuben.hentRute(16));
        kuben.settRute(16, kuben.hentRute(14));
        kuben.settRute(14, temp2);

        String temp3 = kuben.hentRute(51);
        String temp4 = kuben.hentRute(48);
        String temp5 = kuben.hentRute(45);

        kuben.settRute(51, kuben.hentRute(24));
        kuben.settRute(48, kuben.hentRute(21));
        kuben.settRute(45, kuben.hentRute(18));

        kuben.settRute(24, kuben.hentRute(6));
        kuben.settRute(21, kuben.hentRute(3));
        kuben.settRute(18, kuben.hentRute(0));

        kuben.settRute(6, kuben.hentRute(38));
        kuben.settRute(3, kuben.hentRute(41));
        kuben.settRute(0, kuben.hentRute(44));

        kuben.settRute(38, temp3);
        kuben.settRute(41, temp4);
        kuben.settRute(44, temp5);

    }

    @Override
    void vriMot(){

        String temp = kuben.hentRute(9);
        kuben.settRute(9, kuben.hentRute(11));
        kuben.settRute(11, kuben.hentRute(17));
        kuben.settRute(17, kuben.hentRute(15));
        kuben.settRute(15, temp);

        String temp2 = kuben.hentRute(10);
        kuben.settRute(10, kuben.hentRute(14));
        kuben.settRute(14, kuben.hentRute(16));
        kuben.settRute(16, kuben.hentRute(12));
        kuben.settRute(12, temp2);

        String temp3 = kuben.hentRute(51);
        String temp4 = kuben.hentRute(48);
        String temp5 = kuben.hentRute(45);

        kuben.settRute(51, kuben.hentRute(38));
        kuben.settRute(48, kuben.hentRute(41));
        kuben.settRute(45, kuben.hentRute(44));

        kuben.settRute(38, kuben.hentRute(6));
        kuben.settRute(41, kuben.hentRute(3));
        kuben.settRute(44, kuben.hentRute(0));

        kuben.settRute(6, kuben.hentRute(24));
        kuben.settRute(3, kuben.hentRute(21));
        kuben.settRute(0, kuben.hentRute(18));

        kuben.settRute(24, temp3);
        kuben.settRute(21, temp4);
        kuben.settRute(18, temp5);
    }
}
