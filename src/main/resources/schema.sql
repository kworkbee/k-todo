DROP TABLE IF EXISTS `todo`;
CREATE TABLE `todo` (
                        `id` bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
                        `title` varchar(256) NOT NULL,
                        `content` varchar(256) NOT NULL,
                        `due` timestamp NOT NULL,
                        `created_at` timestamp NOT NULL,
                        `updated_at` timestamp NOT NULL
);
