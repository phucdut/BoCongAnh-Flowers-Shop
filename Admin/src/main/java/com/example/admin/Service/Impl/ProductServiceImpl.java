package com.example.admin.Service.Impl;

import com.example.admin.Converter.ProductConverter;
import com.example.admin.Domain.Product;
import com.example.admin.Domain.ProductDTO;
import com.example.admin.Entity.ProductEntity;
import com.example.admin.Repository.CategoryRepository;
import com.example.admin.Repository.ProductRepository;
import com.example.admin.Service.CategoryService;
import com.example.admin.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${imagePathProduct}")
    private String imagePathProduct;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll().stream().map(ProductConverter::toModel).toList();
    }
    @Override
    public Product getProductById(Long productId) {
        return ProductConverter.toModel(productRepository.findById(productId).orElseThrow());
    }

    @Override
    public void updateProduct(ProductDTO product) {
        // Lấy ProductEntity từ cơ sở dữ liệu hoặc ném ngoại lệ nếu không tìm thấy
        ProductEntity productEntity = productRepository.findById(product.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + product.getId()));

        // Cập nhật tất cả các trường từ Product
        if (product.getImage1() != null && product.getImage2() != null && product.getImage3() != null && product.getImage4() != null && product.getImage5() != null) {
            forSaveImage(product, productEntity);
        }
        productEntity.setName(product.getName());
        productEntity.setOriginal_price(product.getOriginalPrice());
        productEntity.setPrice(newPrice(product.getOriginalPrice(), product.getDiscount()));
        productEntity.setDescription(product.getDescription());
        productEntity.setDetails(product.getDetails());
        productEntity.setDelivery(product.getDelivery());
        productEntity.setSub_info(product.getSubInfo());
        productEntity.setOverall_rating(product.getOverall_rating());
        productEntity.setDiscount(product.getDiscount());
        // Lưu ProductEntity đã cập nhật vào cơ sở dữ liệu
        productRepository.save(productEntity);
    }

    @Override
    public void deleteProductById(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow();
        productEntity.setDeleted(true);
        productRepository.save(productEntity);
    }
    @Override
    public void restoreProductById(Long productId){
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow();
        productEntity.setDeleted(false);
        productRepository.save(productEntity);
    }
    @Override
    public ProductEntity createProduct(ProductDTO productDto) {
        ProductEntity entity = new ProductEntity();
        entity.setName(productDto.getName());
        entity.setDetails(productDto.getDetails());
        forSaveImage(productDto, entity);
        entity.setDelivery(productDto.getDelivery());
        entity.setSub_info(productDto.getSubInfo());
        entity.setOriginal_price(productDto.getOriginalPrice());
        entity.setDiscount(productDto.getDiscount());
        entity.setDescription(productDto.getDescription());
        entity.setPrice(newPrice(productDto.getOriginalPrice(), productDto.getDiscount()));
        System.out.println("lưu");
        return productRepository.save(entity);
//
    }

    private void forSaveImage(ProductDTO productDto, ProductEntity entity) {
        for (int i = 1; i <= 5; i++) {
            switch (i) {
                case 1:
                    File file1 = new File(imagePathProduct + productDto.getName() + "1.png");
                    saveImageProduct(productDto.getImage1(), file1);
                    entity.setImage1(productDto.getName() + "1.png");
                    break;
                case 2:
                    File file2 = new File(imagePathProduct + productDto.getName() + "2.png");
                    saveImageProduct(productDto.getImage2(), file2);
                    entity.setImage2(productDto.getName() + "2.png");
                    break;
                case 3:
                    File file3 = new File(imagePathProduct + productDto.getName() + "3.png");
                    saveImageProduct(productDto.getImage3(), file3);
                    entity.setImage3(productDto.getName() + "3.png");
                    break;
                case 4:
                    File file4 = new File(imagePathProduct + productDto.getName() + "4.png");
                    saveImageProduct(productDto.getImage4(), file4);
                    entity.setImage4(productDto.getName() + "4.png");
                    break;
                case 5:
                    File file5 = new File(imagePathProduct + productDto.getName() + "5.png");
                    saveImageProduct(productDto.getImage5(), file5);
                    entity.setImage5(productDto.getName() + "5.png");
                    break;
            }
        }
    }

    private void saveImageProduct(MultipartFile image, File file) {
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(image.getBytes());
            // Thêm dòng in để kiểm tra
            System.out.println("File has been saved successfully");
        } catch (IOException e) {
            e.printStackTrace(); // Thêm dòng này để in ra thông báo lỗi chi tiết
        }
    }
    @Override
    public Product getById(Long id) {
        return ProductConverter.toModel(productRepository.findById(id).orElseThrow());
    }
    private Long newPrice(Long originalPrice, Long discount) {
        return((100-discount)* originalPrice) / 100;
    }

    @Override
    public void setCategories(ProductEntity entity, ProductDTO productDto) {
//        System.out.println("id" + entity.getId());
        entity.setCategoryEntities(categoryRepository.findAllByIdIn(productDto.getCategoryIds()));
//        System.out.println("size 2: " + entity.getCategoryEntities().size());
    }
    @Override
    public boolean checkNameProduct(String name) {
        ProductEntity productEntity = productRepository.findByName(name).orElse(null);
        return productEntity == null;
    }
}
