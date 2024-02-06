package com.schneewittchen.rosandroid.widgets.testsub;

import com.schneewittchen.rosandroid.model.repositories.rosRepo.node.BaseData;

import geometry_msgs.msg.Twist;
import geometry_msgs.msg.Vector3;

/**
 * TODO: Description
 *
 * @author Nils Rottmann
 * @version 1.0.0
 * @created on 05.05.20
 * @updated on 05.05.20
 * @modified by
 */

public class TestSubData extends BaseData {

    private Twist twist;
    public double x;
    public double y;

    public TestSubData(Twist twist) {

        this.twist = twist;
        Vector3 vecLin = this.twist.getLinear();
        Vector3 vecAng = this.twist.getAngular();
        this.x = vecLin.getX();
        this.y = vecLin.getY();
    }
}
