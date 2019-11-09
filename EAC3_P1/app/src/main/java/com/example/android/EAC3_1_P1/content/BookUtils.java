/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.EAC3_1_P1.content;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample book content.
 */
public class BookUtils {

    // An ArrayList of books
    public static final List<book> book_ITEMS = new ArrayList<>();

    // The ID for the index into book titles.
    public static final String book_ID_KEY = "item_id";

    //public static final String book_MINIATURE;

    // The number of books.
    private static final int COUNT = 7;

    /**
     * A book item represents a book title, and book details.
     */
    public static class book {
        public final String book_title;
        public final String book_author;
        public final String details;
        public final String book_miniature;

        private book(String content, String book_author, String details,String book_miniature) {
            this.book_title = content;
            this.book_author=book_author;
            this.details = details;
            this.book_miniature=book_miniature;
        }
    }

    /**
     * Add an item to the array.
     *
     * @param item Each book
     */
    private static void addItem(book item) {
        book_ITEMS.add(item);
    }

    static {
        // Fill the array with books.
        for (int i = 0; i < COUNT; i++) {
            addItem(createbookAtPosition(i));
        }
    }

    private static book createbookAtPosition(int position) {
        String newTitle;
        String newAuthor;
        String newDetail;
        String newImage;

        switch (position) {
            case 0:
                newTitle = "Alicia en el País de las Maravillas";
                newAuthor="Lewis Carroll";
                newDetail = "\"Las aventuras de Alicia en el país de las maravillas, comúnmente abreviado como Alicia en el país de las maravillas, es una novela de fantasía escrita por el matemático, lógico, fotógrafo y escritor británico Charles Lutwidge Dodgson, bajo el seudónimo de Lewis Carroll, publicada en 1865. La historia cuenta cómo una niña llamada Alicia cae por un agujero, encontrándose en un mundo peculiar, poblado por humanos y criaturas antropomórficas. El libro juega con la lógica, dando a la novela gran popularidad tanto en niños como en adultos. Está considerada una de las mejores novelas del género del Sinsentido. Su narrativa y estructura, junto con sus personajes, han sido una gran influencia tanto en la cultura popular como en la literatura, sobre todo en el género fantástico.\n" +
                        "\n" +
                        "En esta obra aparecen algunos de los personajes más famosos de Lewis Carroll, como el Conejo Blanco, la Liebre de Marzo, el Sombrerero, la Oruga azul, el Gato de Cheshire o la Reina de Corazones;1\u200B quienes han cobrado importancia suficiente como para ser reconocidos fuera del mundo de Alicia. \"\n";
                newImage="alicia";
                break;
            case 1:
                newTitle = "Mujercitas";
                newAuthor="Louise May Alcott";
                newDetail = "\"Mujercitas (en inglés, Little Women or Meg, Jo, Beth and Amy) es una novela de Louisa May Alcott publicada el 30 de septiembre de 1868, que trata la vida de cuatro niñas tras pasar la adolescencia se convierten en mujeres con la Guerra Civil en los Estados Unidos como fondo, entre 1861 y 1865. Está basada en las propias experiencias de la autora cuando era una niña y vivía en la ciudad de Concord, Massachusetts.1\u200B\n" +
                        "\n" +
                        "Esta primera novela tuvo su continuación en 1869 con Aquellas mujercitas (en inglés, Good Wives) que transcurre cuatro años después de Mujercitas (muestra problemática ya de la vida adulta). Ambos libros constituyen lo que en Europa se editó como un solo volumen. Los siguientes trabajos de Alcott: Little Men (Hombrecitos) y Jo's Boys (Los muchachos de Jo), son continuaciones de estas dos novelas, ya que muestran a los hijos, sobrinos y alumnos de las Mujercitas construyendo sus propias vidas. \"\n";
                newImage="mujercitas";
                break;
            case 2:
                newTitle = "Crimen y Castigo";
                newAuthor="Fedor M.Dostoievski";
                newDetail = "\"Crimen y castigo1\u200B(en ruso: Преступле́ние и наказа́ние, romanización: Prestupléniye i nakazániye) es una novela de carácter psicológico escrita por el autor ruso Fiódor Dostoievski. Fue publicada por primera vez en la revista El mensajero ruso, en 1866, en doce partes, y publicada después como novela.2\u200B3\u200B Junto con Guerra y paz de León Tolstói, se considera que la novela es una de las más influyentes e internacionales de la literatura rusa.4\u200B Asimismo, los diálogos mantenidos entre el protagonista, Raskólnikov, y el inspector de policía, son considerados por algunos autores, como el prestigioso literato Stefan Zweig, una de las cimas de la literatura universal.\n" +
                        "\n" +
                        "La historia narra la vida de Rodión Raskólnikov, un estudiante en la capital de la Rusia Imperial, San Petersburgo. Este joven se ve obligado a suspender sus estudios por la miseria en la cual se ve envuelto, a pesar de los esfuerzos realizados por su madre Pulqueria y su hermana Dunia para enviarle dinero. Necesitado de financiación para pagar sus gastos, había recurrido a una anciana prestamista vil y egoísta, en cuya casa empeña algunos objetos de valor.\n" +
                        "\n" +
                        "Su hermana Dunia, con la intención de ayudarlo, acepta la propuesta de matrimonio de un rico abogado, hecho que hace enfadar a su hermano cuando conoce que se ha aceptado la propuesta sin haber sido consultado. Pero aunque no quiera aceptar esta ayuda, Rodión tiene aires de grandeza, y en sus delirios cree ser merecedor de un gran futuro, llegándose a comparar con Napoleón. Así que tiene la idea de matar y robar a Aliona Ivánovna, la vieja usurera. Esa idea le atormenta durante días, y crece en su interior como una alocada semilla. \"\n";
                newImage="crimenycastigo";
                break;
            case 3:
                newTitle = "La casa de los espiritus";
                newAuthor="Isabel Allende";
                newDetail = "\"La casa de los espíritus es la primera novela de la escritora chilena Isabel Allende, publicada en Buenos Aires por Editorial Sudamericana en 1982. Tuvo un éxito inmediato de superventas a nivel internacional, ha sido traducida a numerosos idiomas1\u200B y llevada al cine con el mismo nombre por Bille August; estrenada en 1993, la protagonizaron Jeremy Irons, Meryl Streep, Glenn Close, Winona Ryder y Antonio Banderas .2\u200B También hay adaptaciones al teatro, como la de la dramaturga estadounidense Caridad Svich [1], que se ha presentado en países como Costa Rica [2].3\u200B\n" +
                        "\n" +
                        "Calificada dentro del realismo mágico, la novela incorpora cosas inverosímiles y extrañas a lo ordinario. La historia relata la vida de la familia Trueba a lo largo de cuatro generaciones y sigue los movimientos sociales y políticos del período poscolonial de Chile. Narrada desde la perspectiva de dos de sus protagonistas, los acontecimientos retratados en ella tratan sobre el amor, la familia, la muerte, los fantasmas, las clases sociales, la revolución, la política, los ideales y lo maravilloso. \"\n";
                newImage="lacasadelosespiritus";
                break;
            case 4:
                newTitle = "Cumbres Borrascosas";
                newAuthor="Emily Brönte";
                newDetail = "\"Cumbres Borrascosas (título original en inglés: Wuthering Heights) es la única novela de Emily Brontë. Fue publicada por primera vez en 1847 bajo el seudónimo de Ellis Bell. Su hermana Charlotte editó una segunda edición póstuma.\n" +
                        "\n" +
                        "Aunque ahora se considera un clásico de la literatura inglesa, el recibimiento inicial de Cumbres Borrascosas fue tibio en el mejor de los casos. Su estructura innovadora, que se suele comparar con un conjunto de muñecas de matryoshka, desconcertó a los críticos en un primer momento. Algunos críticos contemporáneos a la autora incluso pensaron que éste era un trabajo anterior, menos maduro, de Charlotte Brontë (que había publicado Jane Eyre ese mismo año bajo el seudónimo de Currer Bell).\"\n";
                newImage="cumbresborrascosas";
                break;
            case 5:
                newTitle = "Dracula";
                newAuthor="Bram Stoker";
                newDetail = "\"Drácula es una novela publicada en 1897 por el irlandés Bram Stoker, quien ha convertido a su protagonista en el vampiro más famoso. Se dice que el escritor se basó en las conversaciones que mantuvo con un erudito húngaro llamado Arminius Vámbéry, quien le habló de Vlad Drăculea. La novela, escrita de manera epistolar, presenta otros temas, como el papel de la mujer en la época victoriana, la sexualidad, la inmigración, el colonialismo o el folclore. Como curiosidad, cabe destacar que Bram Stoker no inventó la leyenda vampírica, pero la influencia de la novela ha logrado llegar al cine, el teatro y la televisión.\n" +
                        "\n" +
                        "Desde su publicación en 1897, la novela nunca ha dejado de estar en circulación, y se suceden nuevas ediciones. Sin embargo, hasta 1983 no abandonó el terreno marginal de la literatura sensacionalista para incorporarse a los clásicos de la Universidad de Oxford. \"\n";
                newImage="dracula";
                break;
            default:
                newTitle = "El gran gatsby";
                newAuthor="F.Scott Fitzgerald";
                newDetail = "\"El gran Gatsby es una novela de 1925 escrita por el autor estadounidense F. Scott Fitzgerald que sigue a un grupo de personajes que viven en la ciudad ficticia de West Egg en la próspera Long Island, en el verano de 1922. La historia hace referencia principalmente al joven y misterioso millonario Jay Gatsby, su pasión quijotesca y la obsesión por la hermosa ex debutante Daisy Buchanan. Considerada como la obra maestra de Fitzgerald, El gran Gatsby explora los temas de decadencia, idealismo, resistencia al cambio, agitación social y el exceso, creando un retrato de la época del jazz, del Art deco o de los locos años veinte que ha sido descrito como una advertencia con respecto al sueño americano.1\u200B2\u200B\n" +
                        "\n" +
                        "Fitzgerald, inspirado por las fiestas a las que había asistido durante sus visitas a la costa norte de Long Island comenzó a planear la novela en 1923, con el deseo de producir, en sus palabras, \"\"algo nuevo, extraordinario, hermoso, simple, pero con un intrincado diseño.\"\"3\u200B El progreso de Fitzgerald fue lento, al completar su primer proyecto luego de un traslado a la Riviera francesa en 1924. Su editor, Maxwell Perkins, sintió que el libro era vago y convenció al autor de revisarlo durante el próximo invierno. Fitzgerald fue ambivalente en varias ocasiones acerca del título del libro y consideraba una variedad de alternativas, incluyendo títulos que hacían referencia al carácter romano Trimalción; el título que fue documentado por última vez que deseaba plasmas era Under the Red, White, and Blue. \"\n";
                newImage="elgrangatsby";
                break;
        }
        return new book(newTitle, newAuthor, newDetail, newImage);
    }
}
