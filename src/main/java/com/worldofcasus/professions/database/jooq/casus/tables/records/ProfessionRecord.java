/*
 * This file is generated by jOOQ.
 */
package com.worldofcasus.professions.database.jooq.casus.tables.records;


import com.worldofcasus.professions.database.jooq.casus.tables.Profession;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProfessionRecord extends UpdatableRecordImpl<ProfessionRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>casus.profession.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>casus.profession.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>casus.profession.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>casus.profession.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Profession.PROFESSION.ID;
    }

    @Override
    public Field<String> field2() {
        return Profession.PROFESSION.NAME;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public ProfessionRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public ProfessionRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public ProfessionRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProfessionRecord
     */
    public ProfessionRecord() {
        super(Profession.PROFESSION);
    }

    /**
     * Create a detached, initialised ProfessionRecord
     */
    public ProfessionRecord(Integer id, String name) {
        super(Profession.PROFESSION);

        setId(id);
        setName(name);
    }
}
