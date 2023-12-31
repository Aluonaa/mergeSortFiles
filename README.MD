Java version 1.8.0_41
Maven version 3.6.1
Dependencies:
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.20</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>io.swagger.core.v3</groupId>
        <artifactId>swagger-annotations</artifactId>
        <version>2.1.6</version>
    </dependency>
Инструкция по запуску:
Пример запуска: java -jar mergeSortFiles.jar -i -a out.txt in.txt in1.txt in2.txt in3.txt
При таком запуске подразумевается, что все указанные файлы хранятся в той же директории, что и jar.
При отсутствии параметра режима сортировки (-a или -d) выбирается сортировка по возрастанию.
Отсутствие параметра типа переменных файлов не допускается (-i или -s), как и выходного файла, который 
должен называться out.txt и никак иначе, в случае ошибки или отсутствия необходимого параметра 
будет выдано соответствующее сообщение.
При вводе несуществующих файлов также будет выдано сообщение. Процесс проверки файлов
происходит до начала сортировки.
Входных файлов должно быть не менее 2-х, иначе сортировка не имеет смысла. Все они должны быть текстового
формата (.txt). Тем не менее, некоторые файлы могут быть пустыми. В случае, если во входных аргументах будет 
лишь один непустой файл, то он и будет получен в выходном файле.
Все содержащие пробелы строки игнорируются сортировкой. Их появление не вызывает ошибок. В итоговом файле
таких строк не будет.
В случае, если в алгоритм попадает неотсортированный файл, его отсортированная начальная часть 
(если такая имеется) попадет в итоговый файл, однако как только попадется неотсортированный элемент, 
обработка этого файла будет прекращена и произойдет переход к следующему файлу.
Все файлы обрабатываются построчно для избежания переполнения памяти. Минусом является то, что 
обработанные ранее файлы (отсортированные) снова необходимо считывать при каждой итерации
(В каждой итерации обрабатывается новый файл и старый, содержащий все предыдущие сортировки).

В данной программе сортируются только файлы со строками. И только по возрастанию. 