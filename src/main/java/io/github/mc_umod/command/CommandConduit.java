package io.github.mc_umod.command;

import io.github.mc_umod.util.NBTUtils;
import net.minecraft.block.Block;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandConduit extends CommandBase {
	
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "commands.conduit.usage";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}
	
	@Override
	public void execute(MinecraftServer sr, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayerMP pl = (EntityPlayerMP) sender;
		int meta = 0;
		if (args.length > 2) {
			try {
				meta = Integer.valueOf(args[2]);
			} catch (Exception ex) {
				throw new WrongUsageException(args[2] + " is not a valide Number", 1);
			}
		}
		if (pl != null) {
			if (pl.getHeldItemMainhand() != null) {
				Block item = getBlockByText(sender, args[1]);
				if (item != null) {
					NBTUtils.addStackToConduit(pl.getHeldItemMainhand(), new ItemStack(item, 1, meta));
				} else {
					sender.addChatMessage(new TextComponentString(args[1] + " is not a valid Item"));
				}
			} else {
				sender.addChatMessage(new TextComponentString("Please Heald a Item in Hand wile performing this"));
			}
		} else {
			sender.addChatMessage(new TextComponentString("Sorry this command is Only able for Players"));
		}
	}
	
	@Override
	public String getCommandName() {
		return "conduit";
	}
	
	/*
	 * @SuppressWarnings("rawtypes") public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) { //return args.length == 1 ? getListOfStringsMatchingLastWord(args, this.getPlayers()) : (args.length == 2 ? func_175762_a(args, Block.REGISTRY.getKeys()) : null); }
	 */
	
}
