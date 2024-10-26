BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "category" (
	"idCategory"	INT AUTO_INCREMENT,
	"categoryName"	varchar(50),
	PRIMARY KEY("idCategory")
);
CREATE TABLE IF NOT EXISTS "location" (
	"idLocation"	INT AUTO_INCREMENT,
	"latitude"	DECIMAL(9, 6),
	"longitude"	DECIMAL(9, 6),
	PRIMARY KEY("idLocation")
);
CREATE TABLE IF NOT EXISTS "restaurant" (
	"idRestaurant"	INTEGER,
	"restaurantName"	VARCHAR(50) NOT NULL,
	"rating"	DECIMAL(2, 1) CHECK("rating" >= 0.0 AND "rating" <= 5.0),
	"photoUrl"	TEXT,
	"idCategory"	INTEGER NOT NULL,
	"idLocation"	INTEGER NOT NULL,
	FOREIGN KEY("idLocation") REFERENCES "location"("idLocation"),
	FOREIGN KEY("idCategory") REFERENCES "category"("idCategory"),
	PRIMARY KEY("idRestaurant")
);
CREATE TABLE IF NOT EXISTS "listMenu" (
	"idMenu"	INTEGER,
	"idRestaurant"	INTEGER NOT NULL,
	"idCategory"	INTEGER NOT NULL,
	"name"	varchar(25),
	"urlPhoto"	TEXT,
	"harga"	DECIMAL(10, 2),
	FOREIGN KEY("idCategory") REFERENCES "category"("idCategory"),
	FOREIGN KEY("idRestaurant") REFERENCES "restaurant"("idRestaurant"),
	PRIMARY KEY("idMenu")
);
CREATE TABLE IF NOT EXISTS "status" (
	"idStatus"	INTEGER,
	"keterangan"	VARCHAR(50),
	PRIMARY KEY("idStatus")
);
CREATE TABLE IF NOT EXISTS "orders" (
	"idOrder"	INTEGER,
	"idStatus"	INTEGER NOT NULL,
	"idMenu"	INTEGER NOT NULL,
	"idCustomer"	INTEGER NOT NULL,
	"tglPemesanan"	DATE,
	FOREIGN KEY("idStatus") REFERENCES "status"("idStatus"),
	FOREIGN KEY("idMenu") REFERENCES "listMenu"("idMenu"),
	FOREIGN KEY("idCustomer") REFERENCES "customer"("idCustomer"),
	PRIMARY KEY("idOrder")
);
CREATE TABLE IF NOT EXISTS "customer" (
	"idCustomer"	INTEGER,
	"customerName"	VARCHAR(50),
	"idWallet"	INTEGER,
	"password"	varchar(8),
	FOREIGN KEY("idWallet") REFERENCES "wallet"("idWallet"),
	PRIMARY KEY("idCustomer")
);
CREATE TABLE IF NOT EXISTS "wallet" (
	"idWallet"	INTEGER,
	"saldo"	DECIMAL(10, 2),
	"walletName"	VARCHAR(50),
	PRIMARY KEY("idWallet")
);
INSERT INTO "category" VALUES (1,'pizza');
INSERT INTO "category" VALUES (2,'burger');
INSERT INTO "category" VALUES (3,'sushi');
INSERT INTO "category" VALUES (4,'coffee');
INSERT INTO "category" VALUES (5,'makan kenyang');
INSERT INTO "location" VALUES (1,-7.75750800855892,110.251030989173);
INSERT INTO "location" VALUES (2,-7.74475103773894,110.24236208978);
INSERT INTO "location" VALUES (3,-7.75784818915366,110.220389433892);
INSERT INTO "restaurant" VALUES (1,'Starbucks',4.8,'https://www.google.com/url?sa=i&url=https%3A%2F%2Fm.antaranews.com%2Fberita%2F2884705%2Fstarbucks-luncurkan-menu-baru-rayakan-dua-dekade-di-indonesia&psig=AOvVaw2DZbMYmVutslbc5BaKg06F&ust=1730027997315000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCJCb1NT2q4kDFQAAAAAdAAAAABAE',4,1);
INSERT INTO "restaurant" VALUES (2,'Pizza hut',4.9,'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/20/9f/25/59/great-food.jpg?w=1200&h=-1&s=1',1,2);
INSERT INTO "restaurant" VALUES (3,'Burger king',4.6,'https://www.google.com/url?sa=i&url=https%3A%2F%2Fbkdelivery.co.id%2F&psig=AOvVaw3vdOmdIjYT6wrykJdkfS_1&ust=1730028153309000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCPiJ76_3q4kDFQAAAAAdAAAAABAE',2,3);
INSERT INTO "restaurant" VALUES (4,'Sushi yahuut',4.5,'https://www.google.com/imgres?q=sushi%20&imgurl=https%3A%2F%2Fm.ftscrt.com%2Ffood%2F130aece4-8c53-4025-a99c-58fb45168c42_lg_sq.jpg&imgrefurl=https%3A%2F%2Fwww.fatsecret.co.id%2Fkalori-gizi%2Fumum%2Fsushi&docid=H31aHaJKyMwu5M&tbnid=LA-6wuuBAHF8PM&vet=12ahUKEwiS17TM96uJAxU_R2wGHTxHFykQM3oECBoQAA..i&w=1080&h=1080&hcb=2&ved=2ahUKEwiS17TM96uJAxU_R2wGHTxHFykQM3oECBoQAA',3,2);
INSERT INTO "restaurant" VALUES (5,'Nasi padang murah',5,'https://www.google.com/imgres?q=nasi%20padang&imgurl=https%3A%2F%2Fcdn.rri.co.id%2Fberita%2F1%2Fimages%2F1689391542821-images_(22)%2F1689391542821-images_(22).jpeg&imgrefurl=https%3A%2F%2Fwww.rri.co.id%2Fkuliner%2F287306%2Fdigemari-masyarakat-ini-asal-usul-nasi-padang&docid=3QGMyTi_Rk4uOM&tbnid=2LNjp2XEgYheWM&vet=12ahUKEwip2on096uJAxUqzjgGHZbfJOgQM3oECBsQAA..i&w=554&h=427&hcb=2&ved=2ahUKEwip2on096uJAxUqzjgGHZbfJOgQM3oECBsQAA',5,1);
INSERT INTO "listMenu" VALUES (1,1,4,'Americano','https://www.google.com/url?sa=i&url=https%3A%2F%2Fexcelso-coffee.com%2Fproduct%2Fhot-iced-americano%2F&psig=AOvVaw0X8ORNFFumPOC0jPMZ4eH1&ust=1730028914153000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCPDmmor6q4kDFQAAAAAdAAAAABAE',20000);
INSERT INTO "listMenu" VALUES (2,2,1,'Pizza reguler','https://www.google.com/imgres?q=pizza%20hut&imgurl=https%3A%2F%2Fwww.yum.com%2Fwps%2Fwcm%2Fconnect%2Fyumbrands%2F77ac5d27-1357-4792-9953-54b11f5ae7dd%2Fyum-com-24-product-PH.jpg%3FMOD%3DAJPERES%26CACHEID%3DROOTWORKSPACE.Z18_5QC4HBC039RJ406SQH4UBH3695-77ac5d27-1357-4792-9953-54b11f5ae7dd-oXSxcXb&imgrefurl=https%3A%2F%2Fwww.yum.com%2Fcompany%2Four-brands%2Fpizza-hut%2F&docid=hH5Fij_330AgQM&tbnid=phFMvnhpKqa5YM&vet=12ahUKEwiVmIXX-quJAxXMamwGHbFmLNYQM3oECBoQAA..i&w=1024&h=683&hcb=2&ved=2ahUKEwiVmIXX-quJAxXMamwGHbFmLNYQM3oECBoQAA',120000);
INSERT INTO "listMenu" VALUES (3,3,2,'Paket hemat','https://www.google.com/imgres?q=burger%20king&imgurl=https%3A%2F%2Fmedia-order.bkdelivery.co.id%2Fthumb%2Fgroup_photo%2F2024%2F9%2F17%2F4myffgsakvzkpcepfbthdn_product_list.webp&imgrefurl=https%3A%2F%2Fbkdelivery.co.id%2F&docid=nwFFen2sMA6W2M&tbnid=_QzbrRC9NYkClM&vet=12ahUKEwiFjf3y-quJAxXuSmwGHQ_iOtIQM3oECGMQAA..i&w=300&h=216&hcb=2&itg=1&ved=2ahUKEwiFjf3y-quJAxXuSmwGHQ_iOtIQM3oECGMQAA',30000);
INSERT INTO "listMenu" VALUES (4,4,3,'paket hemat','https://www.google.com/url?sa=i&url=https%3A%2F%2Fpergikuliner.com%2Fblog%2F24-sushi-di-jakarta-paling-enak-dan-favorit&psig=AOvVaw3pn6QxjKZ8SBYdE_0sIWNi&ust=1730029194264000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCKDty5D7q4kDFQAAAAAdAAAAABAE',25000);
INSERT INTO "listMenu" VALUES (5,5,5,'paket nasi ayam','https://www.google.com/imgres?q=naspad%20ayam%20%5C&imgurl=https%3A%2F%2Fasset.kompas.com%2Fcrops%2Fhec2qAt9Nr88SdD57foLyQWTt94%3D%2F0x37%3A800x570%2F750x500%2Fdata%2Fphoto%2F2022%2F05%2F05%2F62734741ced4b.jpg&imgrefurl=https%3A%2F%2Fregional.kompas.com%2Fread%2F2022%2F05%2F30%2F172155078%2F15-lauk-nasi-padang-populer-dan-nikmat-ternyata-tidak-hanya-rendang%3Fpage%3Dall&docid=ZH79fvkMXkU_SM&tbnid=ttdIM8gV6_PcAM&vet=12ahUKEwjZ-tOs-6uJAxVZ3TgGHUVWINkQM3oECBgQAA..i&w=750&h=500&hcb=2&ved=2ahUKEwjZ-tOs-6uJAxVZ3TgGHUVWINkQM3oECBgQAA',15000);
INSERT INTO "status" VALUES (1,'in progress');
INSERT INTO "status" VALUES (2,'completed');
INSERT INTO "customer" VALUES (1,'Ryoo',1,'12345678');
INSERT INTO "wallet" VALUES (1,1000000,'OVO');
COMMIT;
