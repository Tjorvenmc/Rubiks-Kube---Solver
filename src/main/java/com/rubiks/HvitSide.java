package com.rubiks;
public class HvitSide extends Side {

    HvitSide(Kube kube){
        super(kube);
    }

    public void vriMed(){
        
        String temp = kuben.hentRute(18);
        kuben.settRute(18, kuben.hentRute(24));
        kuben.settRute(24, kuben.hentRute(26));
        kuben.settRute(26, kuben.hentRute(20));
        kuben.settRute(20, temp);

        String temp2 = kuben.hentRute(19);
        kuben.settRute(19, kuben.hentRute(21));
        kuben.settRute(21, kuben.hentRute(25));
        kuben.settRute(25, kuben.hentRute(23));
        kuben.settRute(23, temp2);

        String temp3 = kuben.hentRute(6);
        String temp4 = kuben.hentRute(7);
        String temp5 = kuben.hentRute(8);

        kuben.settRute(6, kuben.hentRute(17));
        kuben.settRute(7, kuben.hentRute(14));
        kuben.settRute(8, kuben.hentRute(11));

        kuben.settRute(17, kuben.hentRute(47));
        kuben.settRute(14, kuben.hentRute(46));
        kuben.settRute(11, kuben.hentRute(45));

        kuben.settRute(47, kuben.hentRute(27));
        kuben.settRute(46, kuben.hentRute(30));
        kuben.settRute(45, kuben.hentRute(33));

        kuben.settRute(33, temp5);
        kuben.settRute(30, temp4);
        kuben.settRute(27, temp3);
    }

    @Override
    void vriMot(){

        String temp = kuben.hentRute(18);
        kuben.settRute(18, kuben.hentRute(20));
        kuben.settRute(20, kuben.hentRute(26));
        kuben.settRute(26, kuben.hentRute(24));
        kuben.settRute(24, temp);

        String temp2 = kuben.hentRute(19);
        kuben.settRute(19, kuben.hentRute(23));
        kuben.settRute(23, kuben.hentRute(25));
        kuben.settRute(25, kuben.hentRute(21));
        kuben.settRute(21, temp2);

        String temp3 = kuben.hentRute(6);
        String temp4 = kuben.hentRute(7);
        String temp5 = kuben.hentRute(8);

        kuben.settRute(6, kuben.hentRute(27));
        kuben.settRute(7, kuben.hentRute(30));
        kuben.settRute(8, kuben.hentRute(33));
        
        kuben.settRute(27, kuben.hentRute(47));
        kuben.settRute(30, kuben.hentRute(46));
        kuben.settRute(33, kuben.hentRute(45));

        kuben.settRute(47, kuben.hentRute(17));
        kuben.settRute(46, kuben.hentRute(14));
        kuben.settRute(45, kuben.hentRute(11));

        kuben.settRute(11, temp5);
        kuben.settRute(14, temp4);
        kuben.settRute(17, temp3);

    }
}
