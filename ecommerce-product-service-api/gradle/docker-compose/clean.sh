#!/usr/bin/env bash
echo "Clean local docker MySQL..."

mysql --protocol=tcp -P 13307 -u root -proot -e "DROP DATABASE IF EXISTS ecommerce_product_mysql;CREATE DATABASE IF NOT EXISTS ecommerce_product_mysql DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_unicode_ci;"