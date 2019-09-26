package com.bodhisatan.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 通过JPA持久化读者
 * 无需自己实现这个接口，它扩展了JpaRepository，Spring Data Jpa在运行时会自动创建它的实现，会提供18个操作实体的方法
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {
}
