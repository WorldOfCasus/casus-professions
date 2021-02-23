package com.worldofcasus.professions.command.node;

import com.worldofcasus.professions.CasusProfessions;
import com.worldofcasus.professions.node.Node;
import com.worldofcasus.professions.node.NodeId;
import com.worldofcasus.professions.node.NodeService;
import com.rpkit.core.service.Services;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static net.md_5.bungee.api.ChatColor.*;
import static net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND;
import static net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT;

public final class NodeViewCommand implements CommandExecutor {

    private static final String USAGE_MESSAGE = RED + "Usage: /node view [node]";
    private static final String NODE_SERVICE_NOT_REGISTERED_ERROR = RED + "No node service registered.";
    private static final String INVALID_NODE = RED + "Could not find that node.";

    private final CasusProfessions plugin;

    public NodeViewCommand(CasusProfessions plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) {
            sender.sendMessage(USAGE_MESSAGE);
            return true;
        }
        NodeService nodeService = Services.INSTANCE.get(NodeService.class);
        if (nodeService == null) {
            sender.sendMessage(NODE_SERVICE_NOT_REGISTERED_ERROR);
            return true;
        }
        Optional<Node> node;
        try {
            int nodeId = Integer.parseInt(args[0]);
            node = nodeService.getNode(new NodeId(nodeId));
        } catch (NumberFormatException exception) {
            String nodeName = args[0];
            node = nodeService.getNode(nodeName);
        }
        if (node.isPresent()) {
            Node value = node.get();
            TextComponent deleteNodeButton = new TextComponent("(x)");
            deleteNodeButton.setHoverEvent(
                    new HoverEvent(
                            SHOW_TEXT,
                            new ComponentBuilder()
                                    .append("Delete node " + value.getName())
                                    .create()
                    )
            );
            deleteNodeButton.setClickEvent(
                    new ClickEvent(
                            RUN_COMMAND,
                            "/node delete " + value.getId().getValue()
                    )
            );
            deleteNodeButton.setColor(RED);
            sender.spigot().sendMessage(new ComponentBuilder()
                    .append("Node " + value.getName() + " ").color(WHITE)
                    .append(deleteNodeButton)
                    .create());
            value.getItems().forEach(item -> {
                String itemName = item.getItem().getType().toString().toLowerCase().replace('_', ' ');
                TextComponent deleteItemButton = new TextComponent("(x)");
                deleteItemButton.setHoverEvent(
                        new HoverEvent(
                                SHOW_TEXT,
                                new ComponentBuilder()
                                        .append("Remove item " + itemName)
                                        .create()
                        )
                );
                deleteItemButton.setClickEvent(
                        new ClickEvent(
                                RUN_COMMAND,
                                "/node removeitem " + value.getId().getValue() + " " + item.getId().getValue()
                        )
                );
                deleteItemButton.setColor(RED);
                sender.spigot().sendMessage(new ComponentBuilder()
                        .append(itemName).color(WHITE)
                        .append(" ")
                        .append(deleteItemButton)
                        .create());
            });
            TextComponent addItemButton = new TextComponent("Add item");
            addItemButton.setHoverEvent(
                    new HoverEvent(
                            SHOW_TEXT,
                            new ComponentBuilder()
                                    .append("Add held item to drops")
                                    .create()
                    )
            );
            addItemButton.setClickEvent(
                    new ClickEvent(
                            RUN_COMMAND,
                            "/node additem " + value.getId().getValue()
                    )
            );
            addItemButton.setColor(GREEN);
            sender.spigot().sendMessage(addItemButton);
        } else {
            sender.sendMessage(INVALID_NODE);
        }
        return true;
    }
}
