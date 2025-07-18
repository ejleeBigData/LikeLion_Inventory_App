package com.leeej.inventory_app.query;

import com.leeej.inventory_app.model.Product;
import com.leeej.inventory_app.model.QProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Product> search(ProductSearchCondition condition) {
        QProduct product = QProduct.product;
        BooleanBuilder builder = new BooleanBuilder(); //쿼리 조건 만들어 줌

        if(condition.getName() != null && !condition.getName().isBlank()) {
            builder.and(product.name.containsIgnoreCase(condition.getName())); //containsIgnoreCase 대,소문자 무시
        }
        if(condition.getMinPrice() != null ) {
            builder.and(product.price.goe(condition.getMinPrice())); //goe >= (크거나 같다)
        }
        if(condition.getMaxPrice() != null) {
            builder.and(product.price.loe(condition.getMaxPrice())); // loe <= (작거나 같다)
        }

        return queryFactory.selectFrom(product).where(builder).orderBy(product.name.asc()).fetch();
    }
}
