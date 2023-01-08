package com.rubiks;
public class BlaaSide extends Side{

    BlaaSide(Kube kube){
        super(kube);
    }

    void vriMed(){

        String temp = kuben.hentRute(0);
        kuben.settRute(0, kuben.hentRute(6));
        kuben.settRute(6, kuben.hentRute(8));
        kuben.settRute(8, kuben.hentRute(2));
        kuben.settRute(2, temp);

        String temp2 = kuben.hentRute(1);
        kuben.settRute(1, kuben.hentRute(3));
        kuben.settRute(3, kuben.hentRute(7));
        kuben.settRute(7, kuben.hentRute(5));
        kuben.settRute(5, temp2);

        String temp3 = kuben.hentRute(9);
        String temp4 = kuben.hentRute(10);
        String temp5 = kuben.hentRute(11);

        kuben.settRute(9, kuben.hentRute(18));
        kuben.settRute(10, kuben.hentRute(19));
        kuben.settRute(11, kuben.hentRute(20));

        kuben.settRute(18, kuben.hentRute(27));
        kuben.settRute(19, kuben.hentRute(28));
        kuben.settRute(20, kuben.hentRute(29));

        kuben.settRute(27, kuben.hentRute(36));
        kuben.settRute(28, kuben.hentRute(37));
        kuben.settRute(29, kuben.hentRute(38));

        kuben.settRute(36, temp3);
        kuben.settRute(37, temp4);
        kuben.settRute(38, temp5);
    }

    @Override
    void vriMot(){

        String temp = kuben.hentRute(0);
        kuben.settRute(0, kuben.hentRute(2));
        kuben.settRute(2, kuben.hentRute(8));
        kuben.settRute(8, kuben.hentRute(6));
        kuben.settRute(6, temp);

        String temp2 = kuben.hentRute(1);
        kuben.settRute(1, kuben.hentRute(5));
        kuben.settRute(5, kuben.hentRute(7));
        kuben.settRute(7, kuben.hentRute(3));
        kuben.settRute(3, temp2);

        String temp3 = kuben.hentRute(9);
        String temp4 = kuben.hentRute(10);
        String temp5 = kuben.hentRute(11);

        kuben.settRute(9, kuben.hentRute(36));
        kuben.settRute(10, kuben.hentRute(37));
        kuben.settRute(11, kuben.hentRute(38));

        kuben.settRute(36, kuben.hentRute(27));
        kuben.settRute(37, kuben.hentRute(28));
        kuben.settRute(38, kuben.hentRute(29));

        kuben.settRute(27, kuben.hentRute(18));
        kuben.settRute(28, kuben.hentRute(19));
        kuben.settRute(29, kuben.hentRute(20));

        kuben.settRute(18, temp3);
        kuben.settRute(19, temp4);
        kuben.settRute(20, temp5);

    }
}
