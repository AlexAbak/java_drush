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

package ru.myweek_end.drush.executor;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;

/**
 * Исполнитель Drush.
 * 
 * @author <a href="https://myweek-end.ru/">Моя неделя завершилась</a>
 * @author <a href="mailto:drum@pisem.net">Алексей Кляузер</a>
 * @since 0.0.1.2
 * @version 0.0.1.8
 */
public class Executor {

  /**
   * Путь к корню Drush.
   *
   * @since 0.0.1.4
   */
  private File drushBin;

  /**
   * Путь к запускаемому файлу Drush.
   *
   * @since 0.0.1.4
   */
  private File drushExe;

  /**
   * Получить путь к корню Drush.
   * 
   * @since 0.0.1.4
   * @return Путь к корню Drush.
   */
  public final File getDrushBin() {
    return this.drushBin;
  }

  /**
   * Конструктор.
   *
   * @since 0.0.1.4
   * @param drushBin
   *          Путь к корню Drush.
   */
  public Executor(final File drushBin) {
    this.drushBin = drushBin;
    if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
      drushExe = drushBin.toPath().resolve("drush.bat").toFile();
    } else {
      drushExe = drushBin.toPath().resolve("drush").toFile();
    }
  }

  /**
   * Вывод отладочной информации.
   * 
   * @since 0.0.1.4
   */
  private boolean debug;

  /**
   * Отвечать "нет" на все вопросы.
   * 
   * @since 0.0.1.4
   */
  private boolean no;

  /**
   * Отвечать "да" на все вопросы.
   * 
   * @since 0.0.1.4
   */
  private boolean yes;

  /**
   * Корневая директория Drupal.
   * 
   * @since 0.0.1.4
   */
  private Path root;

  /**
   * Симулировать все действия не внося изменений.
   * 
   * @since 0.0.1.4
   */
  private boolean simulate;

  /**
   * Uri Drupal сайта. Используется при мультисайтинге или нестандартных портах.
   * 
   * @since 0.0.1.4
   */
  private URI uri;

  /**
   * Установить "Вывод отладочной информации".
   *
   * @since 0.0.1.8
   * @param value Вывод отладочной информации.
   */
  public final void setDebug(final boolean value) {
    this.debug = value;
  }

  /**
   * Установить "Отвечать "нет" на все вопросы".
   * 
   * @since 0.0.1.8
   * @param value Отвечать "нет" на все вопросы.
   */
  public final void setNo(final boolean value) {
    this.no = value;
  }

  /**
   * Установить "Отвечать "да" на все вопросы".
   * 
   * @since 0.0.1.8
   * @param value Отвечать "да" на все вопросы.
   */
  public final void setYes(final boolean value) {
    this.yes = value;
  }

  /**
   * Установить "Корневая директория Drupal".
   * 
   * @since 0.0.1.8
   * @param value Корневая директория Drupal.
   */
  public final void setRoot(final Path value) {
    this.root = value;
  }

  /**
   * Установить "Симулировать все действия не внося изменений".
   * 
   * @since 0.0.1.8
   * @param value Симулировать все действия не внося изменений.
   */
  public final void setSimulate(final boolean value) {
    this.simulate = value;
  }

  /**
   * Установить "Uri Drupal сайта".
   * 
   * @since 0.0.1.8
   * @param value Uri Drupal сайта.
   */
  public final void setUri(final URI value) {
    this.uri = value;
  }

  /**
   * Подготовка заготовки процесса.
   *
   * @since 0.0.1.4
   * @return Заготовка процесса
   */
  public ProcessBuilder initProcessBuilder() {
    ProcessBuilder processBuilder;
    processBuilder = new ProcessBuilder(this.drushExe.toString());
    if (this.debug) {
      processBuilder.command().add("--debug");
    }
    if (this.no) {
      processBuilder.command().add("--no");
    }
    if (this.yes) {
      processBuilder.command().add("--yes");
    }
    if (!"".equals(this.root)) {
      processBuilder.command().add("--root=" + this.root);
    }
    if (this.simulate) {
      processBuilder.command().add("--simulate");
    }
    if (null != this.uri) {
      processBuilder.command().add("--uri=" + this.uri.toString());
    }
    return processBuilder;
  }

}
