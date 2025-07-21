package com.leeej.inventory_app.service;

import com.leeej.inventory_app.dto.StockDto;
import com.leeej.inventory_app.dto.StockUpdateDto;
import com.leeej.inventory_app.model.Product;
import com.leeej.inventory_app.model.Stock;
import com.leeej.inventory_app.model.Warehouse;
import com.leeej.inventory_app.query.StockQueryRepository;
import com.leeej.inventory_app.query.StockSearchCondition;
import com.leeej.inventory_app.repository.ProductRepository;
import com.leeej.inventory_app.repository.StockRepository;
import com.leeej.inventory_app.repository.WarehouseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class StockService {
    private final StockRepository stockRepository;
    private final StockQueryRepository queryRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public List<Stock> search(StockSearchCondition condition) {
        return queryRepository.search(condition);
    }

    public Stock getById(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("재고를 찾을 수 없습니다."));
    }

    public Stock create(StockDto dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new NoSuchElementException("제품을 찾을 수 없습니다."));

        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(() -> new NoSuchElementException("창고를 찾을 수 없습니다."));

        Stock stock = new Stock();
        stock.setProduct(product);
        stock.setWarehouse(warehouse);
        stock.setQuantity(dto.getQuantity());

        return stockRepository.save(stock);
    }

    public Stock update(Long id, StockUpdateDto dto) {
        Stock stock = getById(id);
        stock.setQuantity(dto.getQuantity());

        return stockRepository.save(stock);
    }

    public void delete(Long id) {
        stockRepository.deleteById(id);
    }

}
