package com.example.admin.Service;

import com.example.admin.Domain.Item;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface ItemService {
    List<Item> getAllItem();
//    void addItem(Item item, RedirectAttributes redirectAttributes);
    boolean addItem(Item item);
    Item getItemById(Long itemId);

    void updateItem(Item item);

    void deleteItemById(Long itemId);

    void restoreItemById(Long itemId);

    boolean  existsByName(String name);
}
