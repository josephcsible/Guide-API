package amerifrance.guideapi.wrappers;

import amerifrance.guideapi.objects.Entry;
import amerifrance.guideapi.util.GuiHelper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;

public class EntryWrapper extends AbstractWrapper {

    public Entry entry;
    public int x, y, width, height;
    public EntityPlayer player;
    public FontRenderer renderer;

    public EntryWrapper(Entry entry, int x, int y, int width, int height, EntityPlayer player, FontRenderer renderer) {
        this.entry = entry;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.player = player;
        this.renderer = renderer;
    }

    @Override
    public void onClicked() {
        System.out.println(entry.getLocalizedName());
    }

    @Override
    public void onHoverOver(int mouseX, int mouseY) {
        //Minecraft.getMinecraft().fontRenderer.drawString(entry.getLocalizedName(), mouseX + 6, mouseY, 0);
    }

    @Override
    public boolean canPlayerSee(EntityPlayer player) {
        return true;
    }

    public boolean canPlayerSee() {
        return canPlayerSee(player);
    }

    @Override
    public void draw() {
        renderer.drawString(entry.getLocalizedName(), x, y, 0);
    }

    @Override
    public boolean isMouseOnWrapper(int mouseX, int mouseY) {
        return GuiHelper.isMouseBetween(mouseX, mouseY, x, y, width, height);
    }
}