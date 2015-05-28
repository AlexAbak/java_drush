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

package ru.myweek_end.drush.executor.pm;

import java.io.File;
import java.net.URL;

import ru.myweek_end.drush.executor.Executor;

/**
 * Загрузка проектов с drupal.org или других источников.
 *
 * @author <a href="https://myweek-end.ru/">Моя неделя завершилась</a>
 * @author <a href="mailto:drum@pisem.net">Алексей Кляузер</a>
 * @since 0.0.1.8
 * @version 0.0.1.8
 */
public class Download {

  /**
   * Исполнитель Drush.
   *
   * @since 0.0.1.8
   */
  private Executor executor;

  /**
   * Конструктор.
   *
   * @since 0.0.1.8
   * @param executor Исполнитель Drush.
   */
  public Download(Executor executor) {
    this.executor = executor;
  }

  /**
   * Директория назначения.
   *
   * @since 0.0.1.8
   */
  private File destination;

  /**
   * Использовать директорию сайта.
   *
   * @since 0.0.1.8
   */
  private String useSiteDir;

  /**
   * Отобразить сведения после загрузки.
   *
   * @since 0.0.1.8
   */
  private boolean notes;

  /**
   * Вариант установки.
   *
   * @since 0.0.1.8
   */
  private String variant;

  /**
   * Выбор конкретной версии проекта.
   *
   * @since 0.0.1.8
   */
  private String select;

  /**
   * Альтернативное имя каталога "drupal-x.y".
   *
   * @since 0.0.1.8
   */
  private String drupalProjectRename;

  /**
   * Основной номер версии drupal.
   *
   * @since 0.0.1.8
   */
  private String defaultMajor;

  /**
   * Пропустить загрузку библиотек.
   *
   * @since 0.0.1.8
   */
  private boolean skip;

  /**
   * Вернуть список имён расширений.
   *
   * @since 0.0.1.8
   */
  private boolean pipe;

  /**
   * Система контроля версий для резервного копирования.
   *
   * @since 0.0.1.8
   */
  private String versionControl;

  /**
   * Кэшировать загруженное.
   *
   * @since 0.0.1.8
   */
  private boolean cache;

  /**
   * Что использовать для загрузки.
   *
   * @since 0.0.1.8
   */
  private String packageHandler;

  /**
   * URL источника проектов.
   *
   * @since 0.0.1.8
   */
  private URL source;

  /**
   * Использовать версии находящиеся в разработке.
   *
   * @since 0.0.1.8
   */
  private boolean dev;

  /**
   * Установить "Директория назначения".
   *
   * @since 0.0.1.8
   * @param destination Директория назначения.
   */
  public final void setDestination(final File destination) {
    this.destination = destination;
  }

  /**
   * Установить "Использовать директорию сайта".
   *
   * @since 0.0.1.8
   * @param useSiteDir Использовать директорию сайта.
   */
  public final void setUseSiteDir(final String useSiteDir) {
    this.useSiteDir = useSiteDir;
  }

  /**
   * Установить "Отобразить сведения после загрузки".
   *
   * @since 0.0.1.8
   * @param notes Отобразить сведения после загрузки.
   */
  public final void setNotes(final boolean notes) {
    this.notes = notes;
  }

  /**
   * Установить "Вариант установки".
   *
   * @since 0.0.1.8
   * @param variant Вариант установки.
   */
  public final void setVariant(final String variant) {
    this.variant = variant;
  }

  /**
   * Установить "Выбор конкретной версии проекта".
   *
   * @since 0.0.1.8
   * @param select Выбор конкретной версии проекта.
   */
  public final void setSelect(final String select) {
    this.select = select;
  }

  /**
   * Установить "Альтернативное имя каталога "drupal-x.y"".
   *
   * @since 0.0.1.8
   * @param drupalProjectRename Альтернативное имя каталога "drupal-x.y".
   */
  public final void setDrupalProjectRename(final String drupalProjectRename) {
    this.drupalProjectRename = drupalProjectRename;
  }

  /**
   * Установить "Основной номер версии drupal".
   *
   * @since 0.0.1.8
   * @param defaultMajor Основной номер версии drupal.
   */
  public final void setDefaultMajor(final String defaultMajor) {
    this.defaultMajor = defaultMajor;
  }

  /**
   * Установить "Пропустить загрузку библиотек".
   *
   * @since 0.0.1.8
   * @param skip Пропустить загрузку библиотек.
   */
  public final void setSkip(final boolean skip) {
    this.skip = skip;
  }

  /**
   * Установить "Вернуть список имён расширений".
   *
   * @since 0.0.1.8
   * @param pipe Вернуть список имён расширений.
   */
  public final void setPipe(final boolean pipe) {
    this.pipe = pipe;
  }

  /**
   * Установить "Система контроля версий для резервного копирования".
   *
   * @since 0.0.1.8
   * @param versionControl Система контроля версий для резервного копирования.
   */
  public final void setVersionControl(final String versionControl) {
    this.versionControl = versionControl;
  }

  /**
   * Установить "Кэшировать загруженное".
   *
   * @since 0.0.1.8
   * @param cache Кэшировать загруженное.
   */
  public final void setCache(final boolean cache) {
    this.cache = cache;
  }

  /**
   * Установить "Что использовать для загрузки".
   *
   * @since 0.0.1.8
   * @param packageHandler Что использовать для загрузки.
   */
  public final void setPackageHandler(final String packageHandler) {
    this.packageHandler = packageHandler;
  }

  /**
   * Установить "URL источника проектов".
   *
   * @since 0.0.1.8
   * @param source URL источника проектов.
   */
  public final void setSource(final URL source) {
    this.source = source;
  }

  /**
   * Установить "Использовать версии находящиеся в разработке".
   *
   * @since 0.0.1.8
   * @param dev Использовать версии находящиеся в разработке.
   */
  public final void setDev(final boolean dev) {
    this.dev = dev;
  }

  /**
   * Подготовка заготовки процесса.
   *
   * @since 0.0.1.4
   * @return Заготовка процесса
   */
  public ProcessBuilder initProcessBuilder() {
    ProcessBuilder processBuilder = this.executor.initProcessBuilder();
    processBuilder.command().add("pm-download");
    if (null != this.destination) {
      processBuilder.command().add("--destination=" + this.destination.toString());
    }
    if (!"".equals(this.useSiteDir)) {
      processBuilder.command().add("--use-site-dir=" + this.useSiteDir);
    }
    if (this.notes) {
      processBuilder.command().add("--notes");
    }
    if (!"".equals(this.variant)) {
      processBuilder.command().add("--variant=" + this.variant);
    }
    if (!"".equals(this.select)) {
      processBuilder.command().add("--select=" + this.select);
    }
    if (!"".equals(this.drupalProjectRename)) {
      processBuilder.command().add("--drupal-project-rename=" + this.drupalProjectRename);
    }
    if (!"".equals(this.defaultMajor)) {
      processBuilder.command().add("--default-major=" + this.defaultMajor);
    }
    if (this.skip) {
      processBuilder.command().add("--skip");
    }
    if (this.pipe) {
      processBuilder.command().add("--pipe");
    }
    if (!"".equals(this.versionControl)) {
      processBuilder.command().add("--version-control=" + this.versionControl);
    }
    if (this.cache) {
      processBuilder.command().add("--cache");
    }
    if (!"".equals(this.packageHandler)) {
      processBuilder.command().add("--package-handler=" + this.packageHandler);
    }
    if (null != this.source) {
      processBuilder.command().add("--source=" + this.source.toString());
    }
    if (this.dev) {
      processBuilder.command().add("--dev");
    }
    return processBuilder;
  }

}
