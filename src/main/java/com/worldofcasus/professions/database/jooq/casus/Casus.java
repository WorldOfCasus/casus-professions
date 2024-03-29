/*
 * This file is generated by jOOQ.
 */
package com.worldofcasus.professions.database.jooq.casus;


import com.worldofcasus.professions.database.jooq.DefaultCatalog;
import com.worldofcasus.professions.database.jooq.casus.tables.CharacterProfession;
import com.worldofcasus.professions.database.jooq.casus.tables.CharacterStamina;
import com.worldofcasus.professions.database.jooq.casus.tables.Node;
import com.worldofcasus.professions.database.jooq.casus.tables.NodeItem;
import com.worldofcasus.professions.database.jooq.casus.tables.Profession;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Casus extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>casus</code>
     */
    public static final Casus CASUS = new Casus();

    /**
     * The table <code>casus.character_profession</code>.
     */
    public final CharacterProfession CHARACTER_PROFESSION = CharacterProfession.CHARACTER_PROFESSION;

    /**
     * The table <code>casus.character_stamina</code>.
     */
    public final CharacterStamina CHARACTER_STAMINA = CharacterStamina.CHARACTER_STAMINA;

    /**
     * The table <code>casus.node</code>.
     */
    public final Node NODE = Node.NODE;

    /**
     * The table <code>casus.node_item</code>.
     */
    public final NodeItem NODE_ITEM = NodeItem.NODE_ITEM;

    /**
     * The table <code>casus.profession</code>.
     */
    public final Profession PROFESSION = Profession.PROFESSION;

    /**
     * No further instances allowed
     */
    private Casus() {
        super("casus", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            CharacterProfession.CHARACTER_PROFESSION,
            CharacterStamina.CHARACTER_STAMINA,
            Node.NODE,
            NodeItem.NODE_ITEM,
            Profession.PROFESSION);
    }
}
