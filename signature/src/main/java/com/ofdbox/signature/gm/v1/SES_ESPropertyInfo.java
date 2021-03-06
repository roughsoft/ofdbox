package com.ofdbox.signature.gm.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.asn1.*;

import java.util.Enumeration;

/*
 * 印章属性
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SES_ESPropertyInfo extends ASN1Object {

    /*
     * 印章类型
     * */
    private ASN1Integer type;
    /*
     * 印章名称
     * */
    private DERUTF8String name;

    /*
     * 签章者证书信息列表
     * */
    private SES_CertList certList;
    /*
     * 印章制作时间
     * */
    private ASN1UTCTime createDate;
    /*
     * 印章有效期起始时间
     * */
    private ASN1UTCTime validStart;
    /*
     * 印章有效期终止时间
     * */
    private ASN1UTCTime validEnd;

    private SES_ESPropertyInfo(ASN1Sequence sequence) {
        Enumeration<Object> emu = sequence.getObjects();
        this.type = ASN1Integer.getInstance(emu.nextElement());
        this.name = DERUTF8String.getInstance(emu.nextElement());
        this.certList = SES_CertList.getInstance(emu.nextElement());
        this.createDate = ASN1UTCTime.getInstance(emu.nextElement());
        this.validStart = ASN1UTCTime.getInstance(emu.nextElement());
        this.validEnd = ASN1UTCTime.getInstance(emu.nextElement());
    }

    public static SES_ESPropertyInfo getInstance(Object o) {
        if (o instanceof SES_ESPropertyInfo) {
            return (SES_ESPropertyInfo) o;
        } else {
            return new SES_ESPropertyInfo(ASN1Sequence.getInstance(o));
        }
    }

    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector vector = new ASN1EncodableVector();
        vector.add(type);
        vector.add(name);
        vector.add(certList);
        vector.add(createDate);
        vector.add(validStart);
        vector.add(validEnd);

        return new DERSequence(vector);
    }
}
