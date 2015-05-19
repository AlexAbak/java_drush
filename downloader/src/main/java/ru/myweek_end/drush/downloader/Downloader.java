/*
 * Copyright © 2015 "Алексей Кляузер <drum@pisem.net>" Все права защищены.
 */

/*
 * This file is part of java_drush.
 *
 * java_drush is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * java_drush is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with java_drush.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Этот файл — часть java_drush.
 *
 * java_drush - свободная программа: вы можете перераспространять ее и/или
 * изменять ее на условиях Афферо Стандартной общественной лицензии GNU в
 * том виде, в каком она была опубликована Фондом свободного программного
 * обеспечения; либо версии 3 лицензии, либо (по вашему выбору) любой более
 * поздней версии.
 *
 * java_drush распространяется в надежде, что она будет полезной, но БЕЗО
 * ВСЯКИХ ГАРАНТИЙ; даже без неявной гарантии ТОВАРНОГО ВИДА или ПРИГОДНОСТИ
 * ДЛЯ ОПРЕДЕЛЕННЫХ ЦЕЛЕЙ. Подробнее см. в Афферо Стандартной общественной
 * лицензии GNU.
 *
 * Вы должны были получить копию Афферо Стандартной общественной лицензии GNU
 * вместе с этой программой. Если это не так, см.
 * <http://www.gnu.org/licenses/>.
 */

package ru.myweek_end.drush.downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.codehaus.plexus.util.IOUtil;

/**
 * Загрузчик Drush.
 * 
 * @author <a href="https://myweek-end.ru/">Моя неделя завершилась</a>
 * @author <a href="mailto:drum@pisem.net">Алексей Кляузер</a>
 * @since 0.0.1.2
 * @version 0.0.1.3
 */
public class Downloader {

  /**
   * Локальный кэш релизов Drush.
   *
   * @since 0.0.1.3
   */
  private Path localCache;

  /**
   * Локальный репозиторий релизов Drush.
   *
   * @since 0.0.1.3
   */
  private Path localRepository;

  /**
   * Репозиторий для скачивания Drush.
   *
   * @since 0.0.1.3
   */
  private URL drushRepository;

  /**
   * Получить локальный кэш релизов Drush.
   *
   * @since 0.0.1.3
   * @return Локальный кэш релизов Drush.
   */
  public final Path getLocalCache() {
    return this.localCache;
  }

  /**
   * Получить локальный репозиторий релизов Drush.
   *
   * @since 0.0.1.3
   * @return Локальный репозиторий релизов Drush.
   */
  public final Path getLocalRepository() {
    return this.localRepository;
  }

  /**
   * Получить репозиторий для скачивания Drush.
   *
   * @since 0.0.1.3
   * @return Репозиторий для скачивания Drush.
   */
  public final URL getDrushRepository() {
    return this.drushRepository;
  }

  /**
   * Получить домашнюю папку java_drush по умолчанию.
   *
   * @since 0.0.1.3
   * @return Домашняя папка java_drush по умолчанию.
   */
  private final static Path defJavaDrushHome() {
    File userHomeFile = new File(System.getProperty("user.home"));
    Path userHome = userHomeFile.toPath();
    return userHome.resolve(".java_drush");
  }

  /**
   * Получить локальный кэш релизов Drush по умолчанию.
   *
   * @since 0.0.1.3
   * @param javaDrushHome
   *          Домашняя папка java_drush.
   * @return Локальный кэш релизов Drush по умолчанию.
   */
  private final static Path defLocalCache(final Path javaDrushHome) {
    return javaDrushHome.resolve("cache");
  }

  /**
   * Получить локальный репозиторий релизов Drush по умолчанию.
   *
   * @since 0.0.1.3
   * @param javaDrushHome
   *          Домашняя папка java_drush.
   * @return Локальный репозиторий релизов Drush по умолчанию.
   */
  private final static Path defLocalRepository(final Path javaDrushHome) {
    return javaDrushHome.resolve("repository");
  }

  /**
   * Получить репозиторий для скачивания Drush по умолчанию.
   *
   * @since 0.0.1.3
   * @return Репозиторий для скачивания Drush по умолчанию.
   */
  private final static URL defDrushRepository() {
    try {
      return new URL("https://github.com/drush-ops/drush/archive");
    } catch (MalformedURLException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Конструктор.
   *
   * @since 0.0.1.3
   * @param localCache
   *          Локальный кэш релизов Drush.
   * @param localRepository
   *          Локальный репозиторий релизов Drush.
   * @param drushRepository
   *          Репозиторий для скачивания Drush.
   */
  public Downloader(final Path localCache, final Path localRepository, final URL drushRepository) {
    if (localCache == null) {
      this.localCache = defLocalCache(defJavaDrushHome());
    } else {
      this.localCache = localCache;
    }
    if (localRepository == null) {
      this.localRepository = defLocalRepository(defJavaDrushHome());
    } else {
      this.localRepository = localRepository;
    }
    if (drushRepository == null) {
      this.drushRepository = defDrushRepository();
    } else {
      this.drushRepository = drushRepository;
    }
  }

  /**
   * Конструктор.
   *
   * @since 0.0.1.3
   * @param localCache
   *          Локальный кэш релизов Drush.
   * @param localRepository
   *          Локальный репозиторий релизов Drush.
   * @param drushRepository
   *          Репозиторий для скачивания Drush.
   */
  public Downloader(final File localCache, final File localRepository, final URL drushRepository) {
    this(localCache.toPath(), localRepository.toPath(), drushRepository);
  }

  /**
   * Конструктор.
   *
   * @since 0.0.1.3
   * @param javaDrushHome
   *          Домашняя папка java_drush.
   * @param drushRepository
   *          Репозиторий для скачивания Drush.
   */
  public Downloader(final Path javaDrushHome, final URL drushRepository) {
    this(defLocalCache(javaDrushHome), defLocalRepository(javaDrushHome), drushRepository);
  }

  /**
   * Конструктор.
   *
   * @since 0.0.1.3
   * @param javaDrushHome
   *          Домашняя папка java_drush.
   * @param drushRepository
   *          Репозиторий для скачивания Drush.
   */
  public Downloader(final File javaDrushHome, final URL drushRepository) {
    this(javaDrushHome.toPath(), drushRepository);
  }

  /**
   * Конструктор.
   *
   * @since 0.0.1.3
   * @param drushRepository
   *          Репозиторий для скачивания Drush.
   */
  public Downloader(final URL drushRepository) {
    this(defLocalCache(defJavaDrushHome()), defLocalRepository(defJavaDrushHome()), drushRepository);
  }

  /**
   * Конструктор.
   *
   * @since 0.0.1.3
   * @param localCache
   *          Локальный кэш релизов Drush.
   * @param localRepository
   *          Локальный репозиторий релизов Drush.
   */
  public Downloader(final Path localCache, final Path localRepository) {
    this(localCache, localRepository, defDrushRepository());
  }

  /**
   * Конструктор.
   *
   * @since 0.0.1.3
   * @param localCache
   *          Локальный кэш релизов Drush.
   * @param localRepository
   *          Локальный репозиторий релизов Drush.
   */
  public Downloader(final File localCache, final File localRepository) {
    this(localCache, localRepository, defDrushRepository());
  }

  /**
   * Конструктор.
   *
   * @since 0.0.1.3
   * @param javaDrushHome
   *          Домашняя папка java_drush.
   */
  public Downloader(final Path javaDrushHome) {
    this(javaDrushHome, defDrushRepository());
  }

  /**
   * Конструктор.
   *
   * @since 0.0.1.3
   * @param javaDrushHome
   *          Домашняя папка java_drush.
   */
  public Downloader(final File javaDrushHome) {
    this(javaDrushHome, defDrushRepository());
  }

  /**
   * Конструктор.
   *
   * @since 0.0.1.3
   */
  public Downloader() {
    this(defJavaDrushHome(), defDrushRepository());
  }

  /**
   * Распаковка zip архива.
   *
   * @since 0.0.1.3
   * @param sourceFile
   *          Исходный zip файл
   * @param destDir
   *          каталог назначения
   * @throws ZipException
   *           при проблемах с архивом
   * @throws IOException
   *           при проблемах с файловой системой
   */
  private final void UnZip(final File sourceFile, final Path destDir) throws ZipException,
      IOException {
    ZipFile zipFile = new ZipFile(sourceFile);
    try {
      for (Enumeration<? extends ZipEntry> entries = zipFile.entries(); entries.hasMoreElements();) {
        ZipEntry entry = entries.nextElement();
        File file = destDir.resolve(entry.getName()).toFile();
        if (entry.isDirectory()) {
          file.mkdirs();
        } else {
          InputStream source = zipFile.getInputStream(entry);
          FileOutputStream output = new FileOutputStream(file);
          IOUtil.copy(source, output);
        }
      }
    } finally {
      zipFile.close();
    }
  }

  /**
   * Проверить и при необходимости создать каталоги.
   *
   * @since 0.0.1.3
   */
  private final void dirs() {
    File localCache = this.localCache.toFile();
    if (!localCache.exists()) {
      localCache.mkdirs();
    }
    File localRepository = this.localRepository.toFile();
    if (!localRepository.exists()) {
      localRepository.mkdirs();
    }
  }

  /**
   * Получить путь к корню существующего Drush.
   *
   * @since 0.0.1.3
   * @param version
   *          Версия.
   * @return Путь к корню Drush.
   * @throws ZipException
   *           при проблемах с архивом
   * @throws IOException
   *           при проблемах с файловой системой
   * @throws DownloaderException
   *           при общих проблемах скачивания
   */
  private final File drushLocalRepositoryBin(final String version) throws ZipException,
      IOException, DownloaderException {
    File versionRepository = this.localRepository.resolve("drush-" + version).toFile();
    if (!versionRepository.exists()) {
      throw new DownloaderException("В локальном репозитории нет папки "
          + versionRepository.toString() + " с искомой версией Drush");
    }
    return versionRepository;
  }

  /**
   * Распаковать и получить путь к корню Drush.
   *
   * @since 0.0.1.3
   * @param version
   *          Версия.
   * @return Путь к корню Drush.
   * @throws ZipException
   *           при проблемах с архивом
   * @throws IOException
   *           при проблемах с файловой системой
   * @throws DownloaderException
   *           при общих проблемах скачивания
   */
  private final File drushLocalCacheBin(String version) throws ZipException, IOException,
      DownloaderException {
    File versionRepository = this.localRepository.resolve("drush-" + version).toFile();
    File versionCache = this.localCache.resolve("drush-" + version + ".zip").toFile();
    if (!versionCache.exists()) {
      throw new DownloaderException("В локальном кэше нет архива " + versionCache.toString()
          + " с искомой версией Drush");
    }
    UnZip(versionCache, localRepository);
    if (!versionRepository.exists()) {
      throw new DownloaderException("Архив " + versionCache.toString()
          + " в локальном кэше не содержит правильную версию Drush");
    }
    return drushLocalRepositoryBin(version);
  }

  /**
   * Скачать, распаковать и получить путь к корню Drush.
   *
   * @since 0.0.1.3
   * @param version
   *          Версия.
   * @return Путь к корню Drush.
   * @throws ZipException
   *           при проблемах с архивом
   * @throws IOException
   *           при проблемах с файловой системой
   * @throws DownloaderException
   *           при общих проблемах скачивания
   */
  private final File drushRepositoryBin(String version) throws ZipException, IOException,
      DownloaderException {
    File versionCache = this.localCache.resolve("drush-" + version + ".zip").toFile();
    URL fullUrl = new URL(this.drushRepository.toString() + "/" + version + ".zip");
    InputStream source = fullUrl.openStream();
    FileOutputStream output = new FileOutputStream(versionCache);
    IOUtil.copy(source, output);
    return drushLocalCacheBin(version);
  }

  /**
   * Получить путь к корню Drush.
   *
   * @since 0.0.1.3
   * @param version
   *          Версия.
   * @return Путь к корню Drush.
   * @throws ZipException
   *           при проблемах с архивом
   * @throws IOException
   *           при проблемах с файловой системой
   * @throws DownloaderException
   *           при общих проблемах скачивания
   */
  public File drushBin(String version) throws ZipException, IOException, DownloaderException {
    this.dirs();
    File versionRepository = this.localRepository.resolve("drush-" + version).toFile();
    if (versionRepository.exists()) {
      return drushLocalRepositoryBin(version);
    }
    File versionCache = this.localCache.resolve("drush-" + version + ".zip").toFile();
    if (versionCache.exists()) {
      return drushLocalCacheBin(version);
    }
    return drushRepositoryBin(version);
  }

}
