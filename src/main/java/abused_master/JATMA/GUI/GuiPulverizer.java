package abused_master.JATMA.GUI;

import abused_master.JATMA.Info;
import abused_master.JATMA.TE.PulverizerContainer;
import abused_master.JATMA.TE.TilePulverizer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiPulverizer extends GuiContainer {
	
	private static final ResourceLocation Pulverizer = new ResourceLocation(Info.MODID, "textures/gui/Pulverizer.png");
    public static final int WIDTH = 176;
    public static final int HEIGHT = 166;

    public GuiPulverizer(TilePulverizer tileEntity, PulverizerContainer container) {
        super(container);
        xSize = WIDTH;
        ySize = HEIGHT;
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(Pulverizer);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
    
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public static final ResourceLocation texture = new ResourceLocation("jatma:textures/gui/Pulverizer.png");
	int xSize, ySize, left, top;

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();

		GlStateManager.color(1F, 1F, 1F);
		mc.getTextureManager().bindTexture(texture);
		drawTexturedModalRect(left, top, 0, 0, xSize, ySize);

		super.drawScreen(mouseX, mouseY, partialTicks);

		String Pulverizer = I18n.translateToLocal("Pulverizer");
		mc.fontRendererObj.drawStringWithShadow(Pulverizer, left + xSize / 2 - mc.fontRendererObj.getStringWidth(Pulverizer) / 2, top - 12, 0xFFFFFF);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void initGui() {
		xSize = 256;
		ySize = 184;
		left = (width - xSize) / 2;
		top = (height - ySize) / 2;
	}
	*/
