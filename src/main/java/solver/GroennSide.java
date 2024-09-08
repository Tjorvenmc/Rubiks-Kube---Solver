package solver;
public class GroennSide extends Side{

    GroennSide(Kube kube){
        super(kube);
    }

    @Override
    void vriMed(){

        String temp = kuben.hentRute(45);
        kuben.settRute(45, kuben.hentRute(51));
        kuben.settRute(51, kuben.hentRute(53));
        kuben.settRute(53, kuben.hentRute(47));
        kuben.settRute(47, temp);

        String temp2 = kuben.hentRute(46);
        kuben.settRute(46, kuben.hentRute(48));
        kuben.settRute(48, kuben.hentRute(52));
        kuben.settRute(52, kuben.hentRute(50));
        kuben.settRute(50, temp2);

        String temp3 = kuben.hentRute(35);
        String temp4 = kuben.hentRute(34);
        String temp5 = kuben.hentRute(33);

        kuben.settRute(35, kuben.hentRute(26));
        kuben.settRute(34, kuben.hentRute(25));
        kuben.settRute(33, kuben.hentRute(24));

        kuben.settRute(26, kuben.hentRute(17));
        kuben.settRute(25, kuben.hentRute(16));
        kuben.settRute(24, kuben.hentRute(15));

        kuben.settRute(17, kuben.hentRute(44));
        kuben.settRute(16, kuben.hentRute(43));
        kuben.settRute(15, kuben.hentRute(42));

        kuben.settRute(44, temp3);
        kuben.settRute(43, temp4);
        kuben.settRute(42, temp5);

    }

    @Override
    void vriMot(){

        String temp = kuben.hentRute(45);
        kuben.settRute(45, kuben.hentRute(47));
        kuben.settRute(47, kuben.hentRute(53));
        kuben.settRute(53, kuben.hentRute(51));
        kuben.settRute(51, temp);

        String temp2 = kuben.hentRute(46);
        kuben.settRute(46, kuben.hentRute(50));
        kuben.settRute(50, kuben.hentRute(52));
        kuben.settRute(52, kuben.hentRute(48));
        kuben.settRute(48, temp2);

        String temp3 = kuben.hentRute(35);
        String temp4 = kuben.hentRute(34);
        String temp5 = kuben.hentRute(33);

        kuben.settRute(35, kuben.hentRute(44));
        kuben.settRute(34, kuben.hentRute(43));
        kuben.settRute(33, kuben.hentRute(42));

        kuben.settRute(44, kuben.hentRute(17));
        kuben.settRute(43, kuben.hentRute(16));
        kuben.settRute(42, kuben.hentRute(15));

        kuben.settRute(17, kuben.hentRute(26));
        kuben.settRute(16, kuben.hentRute(25));
        kuben.settRute(15, kuben.hentRute(24));

        kuben.settRute(26, temp3);
        kuben.settRute(25, temp4);
        kuben.settRute(24, temp5);

    }
}
