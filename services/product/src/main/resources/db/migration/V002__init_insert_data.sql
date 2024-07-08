INSERT INTO category (name, description) VALUES
    ('Electronics', 'Devices and gadgets such as phones, laptops, and more'),
    ('Books', 'Variety of books including novels, textbooks, and more'),
    ('Home Appliances', 'Appliances for home use such as refrigerators, ovens, and more'),
    ('Clothing', 'Apparel for men, women, and children'),
    ('Beauty & Personal Care', 'Products for beauty and personal grooming'),
    ('Sports & Outdoors', 'Equipment and gear for sports and outdoor activities'),
    ('Toys & Games', 'Toys, games, and educational materials for children'),
    ('Automotive', 'Products and accessories for vehicles'),
    ('Furniture', 'Various furniture for home and office use'),
    ('Food & Grocery', 'Food items, snacks, and groceries');

INSERT INTO product (name, description, available_quantity, price, category_id) VALUES
    ('Smartphone', 'Latest model smartphone with 64GB storage', 100, 699.99, 1),
    ('Laptop', '15-inch laptop with 256GB SSD', 50, 999.99, 1),
    ('E-Reader', '6-inch e-reader with adjustable light', 200, 129.99, 1),
    ('Bluetooth Speaker', 'Portable Bluetooth speaker with 10-hour battery', 150, 49.99, 1),
    ('Wireless Headphones', 'Noise-cancelling wireless headphones', 75, 199.99, 1),

    ('Fiction Novel', 'Bestselling fiction novel', 500, 15.99, 2),
    ('Cookbook', 'Healthy recipes for everyday cooking', 300, 25.99, 2),
    ('Textbook', 'Introduction to Computer Science', 100, 79.99, 2),
    ('Graphic Novel', 'Popular graphic novel series', 250, 12.99, 2),
    ('Self-Help Book', 'Guide to personal development and success', 400, 18.99, 2),

    ('Refrigerator', 'Energy-efficient refrigerator with freezer', 30, 499.99, 3),
    ('Microwave Oven', '700W microwave oven with defrost function', 100, 79.99, 3),
    ('Washing Machine', 'Front-loading washing machine', 20, 399.99, 3),
    ('Vacuum Cleaner', 'Cordless vacuum cleaner with HEPA filter', 75, 149.99, 3),
    ('Blender', 'High-power blender for smoothies and more', 150, 89.99, 3),

    ('T-Shirt', '100% cotton t-shirt, various sizes', 300, 9.99, 4),
    ('Jeans', 'Slim fit jeans, various sizes', 200, 39.99, 4),
    ('Jacket', 'Waterproof winter jacket', 50, 99.99, 4),
    ('Sneakers', 'Comfortable athletic sneakers', 100, 59.99, 4),
    ('Dress', 'Elegant evening dress', 75, 79.99, 4),

    ('Shampoo', 'Natural shampoo for all hair types', 500, 7.99, 5),
    ('Face Cream', 'Moisturizing face cream with SPF', 250, 19.99, 5),
    ('Lipstick', 'Long-lasting matte lipstick', 400, 9.99, 5),
    ('Body Lotion', 'Hydrating body lotion', 300, 12.99, 5),
    ('Perfume', 'Signature fragrance for women', 150, 49.99, 5),

    ('Yoga Mat', 'Eco-friendly yoga mat', 200, 24.99, 6),
    ('Basketball', 'Official size basketball', 150, 29.99, 6),
    ('Tent', '4-person camping tent', 75, 99.99, 6),
    ('Fitness Tracker', 'Fitness tracker with heart rate monitor', 100, 59.99, 6),
    ('Hiking Boots', 'Durable hiking boots', 50, 89.99, 6),

    ('Toy Car', 'Remote control toy car', 300, 19.99, 7),
    ('Board Game', 'Family board game', 150, 29.99, 7),
    ('Puzzle', '1000-piece jigsaw puzzle', 200, 14.99, 7),
    ('Doll', 'Fashion doll with accessories', 250, 24.99, 7),
    ('Building Blocks', 'Set of colorful building blocks', 300, 34.99, 7),

    ('Car Battery', 'High-performance car battery', 50, 89.99, 8),
    ('Oil Filter', 'Oil filter for various car models', 150, 9.99, 8),
    ('Tire', 'All-season tire', 100, 79.99, 8),
    ('Car Vacuum', 'Portable car vacuum cleaner', 200, 29.99, 8),
    ('Car Cover', 'Waterproof car cover', 75, 39.99, 8),

    ('Sofa', 'Comfortable 3-seater sofa', 30, 499.99, 9),
    ('Dining Table', 'Wooden dining table with 4 chairs', 20, 399.99, 9),
    ('Office Chair', 'Ergonomic office chair', 50, 129.99, 9),
    ('Bookshelf', '5-tier bookshelf', 75, 79.99, 9),
    ('Bed Frame', 'Queen-size bed frame', 40, 199.99, 9),

    ('Organic Apple', 'Fresh organic apples', 500, 0.99, 10),
    ('Milk', 'Gallon of whole milk', 200, 3.49, 10),
    ('Bread', 'Whole grain bread', 300, 2.99, 10),
    ('Pasta', 'Pack of whole wheat pasta', 250, 1.49, 10),
    ('Coffee', 'Ground coffee, 12oz', 150, 5.99, 10);
