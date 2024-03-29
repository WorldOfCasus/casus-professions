package com.worldofcasus.professions.database.table;

import com.worldofcasus.professions.CasusProfessions;
import com.worldofcasus.professions.database.Database;
import com.worldofcasus.professions.node.Node;
import com.worldofcasus.professions.node.NodeId;
import com.worldofcasus.professions.node.NodeItem;
import com.worldofcasus.professions.node.NodeItemId;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jooq.Record;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.worldofcasus.professions.database.jooq.casus.Tables.NODE;
import static com.worldofcasus.professions.database.jooq.casus.Tables.NODE_ITEM;
import static java.util.logging.Level.SEVERE;
import static org.jooq.impl.DSL.constraint;

public final class NodeItemTable implements Table {

    private final CasusProfessions plugin;
    private final Database database;

    public NodeItemTable(CasusProfessions plugin, Database database) {
        this.plugin = plugin;
        this.database = database;
    }

    @Override
    public void create() {
        database.create()
                .createTableIfNotExists(NODE_ITEM)
                .columns(
                        NODE_ITEM.ID,
                        NODE_ITEM.NODE_ID,
                        NODE_ITEM.ITEM,
                        NODE_ITEM.CHANCE
                )
                .constraints(
                        constraint("pk_node_item").primaryKey(NODE_ITEM.ID),
                        constraint("fk_node_item_node_id").foreignKey(NODE_ITEM.NODE_ID).references(NODE, NODE.ID)
                )
                .execute();
    }

    public CompletableFuture<List<NodeItem>> get(NodeId nodeId) {
        return CompletableFuture.supplyAsync(() ->
                database.create()
                        .select(
                                NODE_ITEM.ID,
                                NODE_ITEM.NODE_ID,
                                NODE_ITEM.ITEM,
                                NODE_ITEM.CHANCE
                        )
                        .from(NODE_ITEM)
                        .where(NODE_ITEM.NODE_ID.eq(nodeId.getValue()))
                        .fetch()
                        .map(this::toDomain)
        );
    }

    public CompletableFuture<Optional<NodeItem>> get(NodeItemId nodeItemId) {
        return CompletableFuture.supplyAsync(() -> {
            Record result = database.create()
                    .select(
                            NODE_ITEM.ID,
                            NODE_ITEM.NODE_ID,
                            NODE_ITEM.ITEM,
                            NODE_ITEM.CHANCE
                    )
                    .from(NODE_ITEM)
                    .where(NODE_ITEM.ID.eq(nodeItemId.getValue()))
                    .fetchOne();
            if (result == null) return Optional.empty();
            return Optional.of(toDomain(result));
        });
    }

    public CompletableFuture<Optional<NodeItem>> insert(NodeId nodeId, NodeItem item) {
        return CompletableFuture.supplyAsync(() ->
                database.create()
                        .insertInto(NODE_ITEM)
                        .set(NODE_ITEM.NODE_ID, nodeId.getValue())
                        .set(NODE_ITEM.ITEM, byteArrayFromItemStack(item.getItem()))
                        .set(NODE_ITEM.CHANCE, item.getChance())
                        .returning()
                        .fetchOptional()
                        .map(this::toDomain)
        );
    }

    public CompletableFuture<Void> delete(NodeItem item) {
        return CompletableFuture.runAsync(() -> database.create()
                .deleteFrom(NODE_ITEM)
                .where(NODE_ITEM.ID.eq(item.getId().getValue()))
                .execute());
    }

    public CompletableFuture<Void> delete(Node node) {
        return CompletableFuture.runAsync(() ->
                database.create()
                        .deleteFrom(NODE_ITEM)
                        .where(NODE_ITEM.NODE_ID.eq(node.getId().getValue()))
                        .execute()
        );
    }

    private NodeItem toDomain(Record record) {
        if (record == null) return null;
        return new NodeItem(
                new NodeItemId(record.get(NODE_ITEM.ID)),
                itemStackFromByteArray(record.get(NODE_ITEM.ITEM)),
                record.get(NODE_ITEM.CHANCE)
        );
    }

    private ItemStack itemStackFromByteArray(byte[] bytes) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes); BukkitObjectInputStream bois = new BukkitObjectInputStream(bais)) {
            return (ItemStack) bois.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            plugin.getLogger().log(SEVERE, "Failed to load ItemStack", exception);
        }
        return null;
    }

    private byte[] byteArrayFromItemStack(ItemStack item) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); BukkitObjectOutputStream boos = new BukkitObjectOutputStream(baos)) {
            boos.writeObject(item);
            return baos.toByteArray();
        } catch (IOException exception) {
            plugin.getLogger().log(SEVERE, "Failed to save ItemStack", exception);
        }
        return new byte[0];
    }

}
