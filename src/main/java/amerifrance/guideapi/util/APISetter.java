package amerifrance.guideapi.util;

import amerifrance.guideapi.GuideMod;
import amerifrance.guideapi.api.GuideAPI;
import amerifrance.guideapi.api.impl.Book;
import com.google.common.base.Throwables;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * This class is for internal use <b>only</b>. Do not use this from outside.
 */
@SuppressWarnings("unchecked")
public class APISetter {

    public static void registerBook(Book book) {
        try {
            sanityCheck();
        } catch (IllegalAccessException e) {
            Throwables.propagate(e);
            return;
        }

        try {
            Field books = GuideAPI.class.getDeclaredField("BOOKS");
            books.setAccessible(true);
            Map<ResourceLocation, Book> BOOKS = (Map<ResourceLocation, Book>) books.get(null);
            BOOKS.put(book.getRegistryName(), book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setBookForStack(Book book, ItemStack stack) {
        try {
            sanityCheck();
        } catch (IllegalAccessException e) {
            Throwables.propagate(e);
            return;
        }

        try {
            Field stacks = GuideAPI.class.getDeclaredField("BOOK_TO_STACK");
            stacks.setAccessible(true);
            Map<Book, ItemStack> BOOK_TO_STACK = (Map<Book, ItemStack>) stacks.get(null);
            BOOK_TO_STACK.put(book, stack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setIndexedBooks(List<Book> books) {
        try {
            sanityCheck();
        } catch (IllegalAccessException e) {
            Throwables.propagate(e);
            return;
        }

        try {
            Field indexedBooks = GuideAPI.class.getDeclaredField("indexedBooks");
            indexedBooks.setAccessible(true);
            indexedBooks.set(null, books);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sanityCheck() throws IllegalAccessException {
        ModContainer activeMod = Loader.instance().activeModContainer();
        if (!activeMod.getModId().equals(GuideMod.ID))
            throw new IllegalAccessException("Mod " + activeMod.getModId() + " tried to access an internal-only method in GuideAPI. Please report this.");
    }
}
