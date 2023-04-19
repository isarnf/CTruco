/*
 *  Copyright (C) 2021 Lucas B. R. de Oliveira - IFSP/SCL
 *  Contact: lucas <dot> oliveira <at> ifsp <dot> edu <dot> br
 *
 *  This file is part of CTruco (Truco game for didactic purpose).
 *
 *  CTruco is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  CTruco is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with CTruco.  If not, see <https://www.gnu.org/licenses/>
*/

package com.caueisa.destroyerbot;

import com.bueno.spi.model.CardRank;
import com.bueno.spi.model.CardSuit;
import com.bueno.spi.model.GameIntel;
import com.bueno.spi.model.TrucoCard;
import com.bueno.spi.service.BotServiceProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DestroyerBotTest {

    @Mock
    private GameIntel intel;
    private final BotServiceProvider sut = new DestroyerBot();

    private TrucoCard vira;
    private List<TrucoCard> cards;
    private Optional<TrucoCard> opponentCard;

    @Nested
    @DisplayName("When in first round")
    class FirstRoundTest {
        @Nested
        @DisplayName("When playing a card")
        class ChooseCardTest {

            @Test
            @DisplayName("Should play the lowest card that is stronger than the opponent card")
            void shouldPlayTheLowestCardThatIsStrongerThanOpponentCard() {
                vira = TrucoCard.of(CardRank.ACE, CardSuit.SPADES);
                cards = List.of(TrucoCard.of(CardRank.KING, CardSuit.CLUBS),
                                TrucoCard.of(CardRank.SEVEN, CardSuit.HEARTS),
                                TrucoCard.of(CardRank.JACK, CardSuit.DIAMONDS));
                opponentCard = Optional.of(TrucoCard.of(CardRank.SIX, CardSuit.CLUBS));

                when(intel.getVira()).thenReturn(vira);
                when(intel.getCards()).thenReturn(cards);
                when(intel.getOpponentCard()).thenReturn(opponentCard);

                assertThat(sut.chooseCard(intel).content()).isEqualTo(TrucoCard.of(CardRank.SEVEN, CardSuit.HEARTS));
            }
        }
    }

}