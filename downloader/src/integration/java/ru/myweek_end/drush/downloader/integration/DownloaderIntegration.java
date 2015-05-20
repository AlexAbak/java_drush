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

package ru.myweek_end.drush.downloader.integration;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipException;

import org.junit.Before;
import org.junit.Test;

import ru.myweek_end.drush.downloader.Downloader;
import ru.myweek_end.drush.downloader.DownloaderException;

/**
 * Интеграционное тестирование загрузчика Drush.
 * 
 * @author <a href="https://myweek-end.ru/">Моя неделя завершилась</a>
 * @author <a href="mailto:drum@pisem.net">Алексей Кляузер</a>
 * @since 0.0.1.3
 * @version 0.0.1.3
 */
public class DownloaderIntegration {

  /**
   * Загрузчик
   *
   * @since 0.0.1.3
   */
  private Downloader downloader;

  /**
   * Очистка каталога.
   *
   * @since 0.0.1.6
   * @throws Exception
   *           при проблемах очистки
   */
  public final void erase(final File dir) {
    if (dir.exists() && dir.isDirectory()) {
      for (File item : dir.listFiles()) {
        if (item.isDirectory()) {
          erase(item);
        }
        item.delete();
      }
    }
  }

  /**
   * Настройка теста.
   *
   * @since 0.0.1.3
   * @throws java.lang.Exception
   *           при проблемах
   */
  @Before
  public void setUp() throws Exception {
    this.downloader = new Downloader();
  }

  private File expected = new File(System.getProperty("user.home")).toPath()
      .resolve(".java_drush/repository/drush-6.6.0").toFile();

  /**
   * Тестировать полный цикл
   *
   * @since 0.0.1.6
   * @throws DownloaderException
   * @throws ZipException
   *           при проблемах с архивом
   * @throws IOException
   *           при проблемах с файловой системой
   * @throws DownloaderException
   *           при общих проблемах скачивания Test method for
   *           {@link ru.myweek_end.drush.downloader.Downloader#drushBin(java.lang.String)}
   */
  @Test
  public void testDrushBin0() throws ZipException, IOException, DownloaderException {
    File bin = this.downloader.drushBin("6.6.0");
    erase(this.downloader.getLocalRepository().resolve("drush-6.6.0").toFile());
    this.downloader.getLocalRepository().resolve("drush-6.6.0").toFile().delete();
    this.downloader.getLocalCache().resolve("drush-6.6.0.zip").toFile().delete();
    bin = this.downloader.drushBin("6.6.0");
    assertEquals(expected, bin);
  }

  /**
   * Тестировать распаковку
   *
   * @since 0.0.1.6
   * @throws DownloaderException
   * @throws ZipException
   *           при проблемах с архивом
   * @throws IOException
   *           при проблемах с файловой системой
   * @throws DownloaderException
   *           при общих проблемах скачивания Test method for
   *           {@link ru.myweek_end.drush.downloader.Downloader#drushBin(java.lang.String)}
   */
  @Test
  public void testDrushBin1() throws ZipException, IOException, DownloaderException {
    File bin = this.downloader.drushBin("6.6.0");
    this.downloader.getLocalCache().resolve("drush-6.6.0.zip").toFile().delete();
    bin = this.downloader.drushBin("6.6.0");
    assertEquals(expected, bin);
  }

  /**
   * Тестировать возврат
   *
   * @since 0.0.1.6
   * @throws DownloaderException
   * @throws ZipException
   *           при проблемах с архивом
   * @throws IOException
   *           при проблемах с файловой системой
   * @throws DownloaderException
   *           при общих проблемах скачивания Test method for
   *           {@link ru.myweek_end.drush.downloader.Downloader#drushBin(java.lang.String)}
   */
  @Test
  public void testDrushBin2() throws ZipException, IOException, DownloaderException {
    File bin = this.downloader.drushBin("6.6.0");
    bin = this.downloader.drushBin("6.6.0");
    assertEquals(expected, bin);
  }

}
