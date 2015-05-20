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

package ru.myweek_end.drush.downloader.test;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

import org.junit.Test;

import ru.myweek_end.drush.downloader.Downloader;

/**
 * Интеграционное тестирование загрузчика Drush.
 * 
 * @author <a href="https://myweek-end.ru/">Моя неделя завершилась</a>
 * @author <a href="mailto:drum@pisem.net">Алексей Кляузер</a>
 * @since 0.0.1.6
 * @version 0.0.1.6
 */
public class DownloaderTest {

  /**
   * Получить тестовый домашнюю папку java_drush.
   *
   * @since 0.0.1.6
   * @return Домашняя папка java_drush.
   */
  private final static Path defJavaDrushHome() {
    return new File(".java_drush_test").toPath();
  }

  /**
   * Получить тестовый локальный кэш релизов Drush.
   *
   * @since 0.0.1.6
   * @param javaDrushHome
   *          Домашняя папка java_drush.
   * @return Локальный кэш релизов Drush.
   */
  private final static Path defLocalCache(final Path javaDrushHome) {
    return javaDrushHome.resolve("cache_test");
  }

  /**
   * Получить тестовый локальный репозиторий релизов Drush.
   *
   * @since 0.0.1.6
   * @param javaDrushHome
   *          Домашняя папка java_drush.
   * @return Локальный репозиторий релизов Drush.
   */
  private final static Path defLocalRepository(final Path javaDrushHome) {
    return javaDrushHome.resolve("repository_test");
  }

  /**
   * Получить тестовый репозиторий для скачивания Drush.
   *
   * @since 0.0.1.6
   * @return Репозиторий для скачивания Drush.
   */
  private final static URL defDrushRepository0() {
    try {
      return new URL("http://example.org");
    } catch (MalformedURLException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Получить тестовый репозиторий для скачивания Drush.
   *
   * @since 0.0.1.6
   * @return Репозиторий для скачивания Drush.
   */
  private final static URL defDrushRepository1() {
    try {
      return new URL("https://github.com/drush-ops/drush/archive");
    } catch (MalformedURLException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Тестировать Downloader(final Path localCache, final Path localRepository,
   * final URL drushRepository)
   *
   * Test method for
   * {@link ru.myweek_end.drush.downloader.Downloader#Downloader(java.nio.file.Path, java.nio.file.Path, java.net.URL)}
   * .
   *
   * @since 0.0.1.6
   */
  @Test
  public void testDownloaderPathPathURL() {
    Downloader downloader = new Downloader(defLocalCache(defJavaDrushHome()),
        defLocalRepository(defJavaDrushHome()), defDrushRepository0());
    assertEquals(defLocalCache(defJavaDrushHome()), downloader.getLocalCache());
    assertEquals(defLocalRepository(defJavaDrushHome()), downloader.getLocalRepository());
    assertEquals(defDrushRepository0(), downloader.getDrushRepository());
  }

  /**
   * Тестировать Downloader(final File localCache, final File localRepository,
   * final URL drushRepository)
   *
   * Test method for
   * {@link ru.myweek_end.drush.downloader.Downloader#Downloader(java.io.File, java.io.File, java.net.URL)}
   * .
   *
   * @since 0.0.1.6
   */
  @Test
  public void testDownloaderFileFileURL() {
    Downloader downloader = new Downloader(defLocalCache(defJavaDrushHome()).toFile(),
        defLocalRepository(defJavaDrushHome()).toFile(), defDrushRepository0());
    assertEquals(defLocalCache(defJavaDrushHome()), downloader.getLocalCache());
    assertEquals(defLocalRepository(defJavaDrushHome()), downloader.getLocalRepository());
    assertEquals(defDrushRepository0(), downloader.getDrushRepository());
  }

  /**
   * Тестировать Downloader(final Path javaDrushHome, final URL drushRepository)
   *
   * Test method for
   * {@link ru.myweek_end.drush.downloader.Downloader#Downloader(java.nio.file.Path, java.net.URL)}
   * .
   *
   * @since 0.0.1.6
   */
  @Test
  public void testDownloaderPathURL() {
    Downloader downloader = new Downloader(defJavaDrushHome(), defDrushRepository0());
    assertEquals(defJavaDrushHome().resolve("cache"), downloader.getLocalCache());
    assertEquals(defJavaDrushHome().resolve("repository"), downloader.getLocalRepository());
    assertEquals(defDrushRepository0(), downloader.getDrushRepository());
  }

  /**
   * Тестировать Downloader(final File javaDrushHome, final URL drushRepository)
   *
   * Test method for
   * {@link ru.myweek_end.drush.downloader.Downloader#Downloader(java.io.File, java.net.URL)}
   * .
   *
   * @since 0.0.1.6
   */
  @Test
  public void testDownloaderFileURL() {
    Downloader downloader = new Downloader(defJavaDrushHome().toFile(), defDrushRepository0());
    assertEquals(defJavaDrushHome().resolve("cache"), downloader.getLocalCache());
    assertEquals(defJavaDrushHome().resolve("repository"), downloader.getLocalRepository());
    assertEquals(defDrushRepository0(), downloader.getDrushRepository());
  }

  /**
   * Тестировать Downloader(final URL drushRepository)
   *
   * Test method for
   * {@link ru.myweek_end.drush.downloader.Downloader#Downloader(java.net.URL)}.
   *
   * @since 0.0.1.6
   */
  @Test
  public void testDownloaderURL() {
    Downloader downloader = new Downloader(defDrushRepository0());
    assertEquals(new File(System.getProperty("user.home")).toPath().resolve(".java_drush/cache"),
        downloader.getLocalCache());
    assertEquals(
        new File(System.getProperty("user.home")).toPath().resolve(".java_drush/repository"),
        downloader.getLocalRepository());
    assertEquals(defDrushRepository0(), downloader.getDrushRepository());
  }

  /**
   * Тестировать Downloader(final Path localCache, final Path localRepository)
   *
   * Test method for
   * {@link ru.myweek_end.drush.downloader.Downloader#Downloader(java.nio.file.Path, java.nio.file.Path)}
   * .
   *
   * @since 0.0.1.6
   */
  @Test
  public void testDownloaderPathPath() {
    Downloader downloader = new Downloader(defLocalCache(defJavaDrushHome()),
        defLocalRepository(defJavaDrushHome()));
    assertEquals(defLocalCache(defJavaDrushHome()), downloader.getLocalCache());
    assertEquals(defLocalRepository(defJavaDrushHome()), downloader.getLocalRepository());
    assertEquals(defDrushRepository1(), downloader.getDrushRepository());
  }

  /**
   * Тестировать Downloader(final File localCache, final File localRepository)
   * Test method for
   * {@link ru.myweek_end.drush.downloader.Downloader#Downloader(java.io.File, java.io.File)}
   * .
   *
   * @since 0.0.1.6
   */
  @Test
  public void testDownloaderFileFile() {
    Downloader downloader = new Downloader(defLocalCache(defJavaDrushHome()).toFile(),
        defLocalRepository(defJavaDrushHome()).toFile());
    assertEquals(defLocalCache(defJavaDrushHome()), downloader.getLocalCache());
    assertEquals(defLocalRepository(defJavaDrushHome()), downloader.getLocalRepository());
    assertEquals(defDrushRepository1(), downloader.getDrushRepository());
  }

  /**
   * Тестировать Downloader(final Path javaDrushHome)
   *
   * Test method for
   * {@link ru.myweek_end.drush.downloader.Downloader#Downloader(java.nio.file.Path)}
   * .
   *
   * @since 0.0.1.6
   */
  @Test
  public void testDownloaderPath() {
    Downloader downloader = new Downloader(defJavaDrushHome());
    assertEquals(defJavaDrushHome().resolve("cache"), downloader.getLocalCache());
    assertEquals(defJavaDrushHome().resolve("repository"), downloader.getLocalRepository());
    assertEquals(defDrushRepository1(), downloader.getDrushRepository());
  }

  /**
   * Тестировать Downloader(final File javaDrushHome)
   *
   * Test method for
   * {@link ru.myweek_end.drush.downloader.Downloader#Downloader(java.io.File)}.
   *
   * @since 0.0.1.6
   */
  @Test
  public void testDownloaderFile() {
    Downloader downloader = new Downloader(defJavaDrushHome().toFile());
    assertEquals(defJavaDrushHome().resolve("cache"), downloader.getLocalCache());
    assertEquals(defJavaDrushHome().resolve("repository"), downloader.getLocalRepository());
    assertEquals(defDrushRepository1(), downloader.getDrushRepository());
  }

  /**
   * Тестировать Downloader()
   *
   * Test method for
   * {@link ru.myweek_end.drush.downloader.Downloader#Downloader()}.
   *
   * @since 0.0.1.6
   */
  @Test
  public void testDownloader() {
    Downloader downloader = new Downloader();
    assertEquals(new File(System.getProperty("user.home")).toPath().resolve(".java_drush/cache"),
        downloader.getLocalCache());
    assertEquals(
        new File(System.getProperty("user.home")).toPath().resolve(".java_drush/repository"),
        downloader.getLocalRepository());
    assertEquals(defDrushRepository1(), downloader.getDrushRepository());
  }

}
