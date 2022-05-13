package com.bnyte.flow.entity;

import com.bnyte.flow.serializ.HadoopSerializable;

import javax.annotation.Nonnull;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author bnyte
 * @since 2022/5/13 13:25
 */
public class Flow implements HadoopSerializable<Flow> {

    private Long upstream;
    private Long downlink;
    private Long total;

    @Override
    public int compareTo(@Nonnull Flow flow) {
        if (flow == this) return 0;
        if (!this.total.equals(flow.getTotal())) return this.total.compareTo(flow.getTotal());
        if (!this.upstream.equals(flow.getUpstream())) return this.upstream.compareTo(flow.getUpstream());
        if (!this.downlink.equals(flow.getDownlink())) return this.downlink.compareTo(flow.getDownlink());
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upstream);
        dataOutput.writeLong(downlink);
        dataOutput.writeLong(total);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.upstream = dataInput.readLong();
        this.downlink = dataInput.readLong();
        this.total = dataInput.readLong();
    }

    public void initialization(Long upstream, Long downlink) {
        this.setUpstream(upstream);
        this.setDownlink(downlink);
        this.setTotal();
    }

    public void initialization(Long upstream, Long downlink, Long total) {
        this.setUpstream(upstream);
        this.setDownlink(downlink);
        this.setTotal(total);
    }

    public Long getUpstream() {
        return upstream;
    }

    public void setUpstream(Long upstream) {
        this.upstream = upstream;
    }

    public Long getDownlink() {
        return downlink;
    }

    public void setDownlink(Long downlink) {
        this.downlink = downlink;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setTotal() {
        this.total = this.upstream + this.downlink;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s", this.upstream, this.getDownlink(), this.getTotal());
    }
}
