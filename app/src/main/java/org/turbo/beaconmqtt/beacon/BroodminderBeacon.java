package org.turbo.beaconmqtt.beacon;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.altbeacon.beacon.Region;

public final class BroodminderBeacon extends BaseBleBeacon {
    public final static String BEACON_LAYOUT = "m:0-2=8d022b,i:3-3,i:4-4,d:5-8,d:9-12,d:13-16,d:17-20,d:21-21,p:22-22";
    public final static String BROODMINDER_BEACON = "BroodminderBeacon";
    public final static Integer TYPECODE = 0x8D022B;
    @Expose
    private final BroodminderBeaconData broodminderBeaconData = new BroodminderBeaconData();

    public BroodminderBeacon() {
        mType = BROODMINDER_BEACON;
    }

    public BroodminderBeacon(String id, String group, String tag,
                             String major, String minor, String mac) {
        mId = id;
        mGroup = group;
        mTag = tag;
        mType = BROODMINDER_BEACON;
        broodminderBeaconData.mMajor = major;
        broodminderBeaconData.mMinor = minor;
        mBluetoothAddress = mac.trim();
    }

    public BroodminderBeacon(String id, String group, String tag,
                             @NonNull Region region) {
        mId = id;
        mGroup = group;
        mTag = tag;
        mType = BROODMINDER_BEACON;
        broodminderBeaconData.mMajor = Integer.toString(region.getId1().toInt());
        broodminderBeaconData.mMinor = Integer.toString(region.getId2().toInt());
        mBluetoothAddress = region.getBluetoothAddress();
    }

    public BroodminderBeacon(TransactionBeacon transactionBeacon) {
        mId = transactionBeacon.getId();
        mGroup = transactionBeacon.getGroup();
        mTag = transactionBeacon.getTag();
        mType = BROODMINDER_BEACON;
        broodminderBeaconData.mMajor = transactionBeacon.getMajor();
        broodminderBeaconData.mMinor = transactionBeacon.getMinor();
        mBluetoothAddress = transactionBeacon.getMacAddress();
    }

    @Override
    public String getMajor() {
        return broodminderBeaconData.mMajor;
    }

    @Override
    public String getMinor() {
        return broodminderBeaconData.mMinor;
    }

    public boolean isValid() {
        boolean isValid = super.isValid();
        isValid &= Helper.validateInt(broodminderBeaconData.mMajor, 0, 255);
        isValid &= Helper.validateInt(broodminderBeaconData.mMinor, 0, 255);
        isValid &= Helper.validateMacAddress(mBluetoothAddress);
        return isValid;
    }

    private class BroodminderBeaconData {
        @Expose
        @SerializedName("major")
        private String mMajor;
        @Expose
        @SerializedName("minor")
        private String mMinor;
    }
}
