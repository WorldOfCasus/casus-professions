package com.worldofcasus.professions.node;

import java.util.Objects;

public final class NodeId {

    private final int value;

    public NodeId(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeId nodeId = (NodeId) o;
        return value == nodeId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
