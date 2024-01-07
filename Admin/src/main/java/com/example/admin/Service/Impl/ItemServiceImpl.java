package com.example.admin.Service.Impl;

import com.example.admin.Converter.CategoryConverter;
import com.example.admin.Converter.ItemConverter;
import com.example.admin.Domain.Category;
import com.example.admin.Domain.Item;
import com.example.admin.Entity.ItemEntity;
import com.example.admin.Repository.ItemRepository;
import com.example.admin.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAllItem() {
        return itemRepository.findAll().stream().map(ItemConverter::toModel).toList();
    }

    @Override
    public boolean addItem(Item item) {
        // Kiểm tra xem mục có tồn tại trong giỏ hàng không
        Optional<ItemEntity> optionalItem = itemRepository.findAll().stream()
                .filter(entity -> entity.getName().equals(item.getName()))
                .findFirst();
        if (optionalItem.isPresent()) {
            return false;
        } else {
            itemRepository.save(ItemConverter.toEntity(item));
            return true;
        }

    }
    @Override
    public Item getItemById(Long itemId) {
        return ItemConverter.toModel(itemRepository.findById(itemId).orElseThrow());
    }

    @Override
    public void updateItem(Item item) {
        ItemEntity itemEntity = itemRepository.findById(item.getId()).orElseThrow();
        itemEntity.setName(item.getName());

        itemRepository.save(itemEntity);
    }

    @Override
    public void deleteItemById(Long itemId) {
        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow();
        itemEntity.setDeleted(true);
        itemRepository.save(itemEntity);
    }

    @Override
    public void restoreItemById(Long itemId) {
        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow();
        itemEntity.setDeleted(false);
        itemRepository.save(itemEntity);
    }

//    public class DuplicateItemNameException extends RuntimeException {
//        public DuplicateItemNameException(String message) {
//            super(message);
//        }
//    }
    @Override
    public boolean existsByName(String name) {
        return itemRepository.existsByName(name);
    }
}
