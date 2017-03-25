/*
 * This file is part of bisq.
 *
 * bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package io.bisq.gui.main.debug;

import io.bisq.common.taskrunner.Task;
import io.bisq.core.offer.availability.OfferAvailabilityProtocol;
import io.bisq.core.offer.availability.tasks.ProcessOfferAvailabilityResponse;
import io.bisq.core.offer.availability.tasks.SendOfferAvailabilityRequest;
import io.bisq.core.offer.placeoffer.PlaceOfferProtocol;
import io.bisq.core.offer.placeoffer.tasks.AddOfferToRemoteOfferBook;
import io.bisq.core.offer.placeoffer.tasks.BroadcastCreateOfferFeeTx;
import io.bisq.core.offer.placeoffer.tasks.CreateOfferFeeTx;
import io.bisq.core.offer.placeoffer.tasks.ValidateOffer;
import io.bisq.core.trade.protocol.BuyerAsMakerProtocol;
import io.bisq.core.trade.protocol.BuyerAsTakerProtocol;
import io.bisq.core.trade.protocol.SellerAsMakerProtocol;
import io.bisq.core.trade.protocol.SellerAsTakerProtocol;
import io.bisq.core.trade.protocol.tasks.buyer.BuyerSendFiatTransferStartedMessage;
import io.bisq.core.trade.protocol.tasks.buyer_as_maker.BuyerAsMakerCreatesAndSignsDepositTx;
import io.bisq.core.trade.protocol.tasks.buyer_as_taker.BuyerAsTakerSignAndFinalizePayoutTx;
import io.bisq.core.trade.protocol.tasks.buyer_as_taker.BuyerAsTaker___ProcessFinalizePayoutTxRequest;
import io.bisq.core.trade.protocol.tasks.buyer_as_taker.BuyerAsTaker___SendPayoutTxFinalizedMessage;
import io.bisq.core.trade.protocol.tasks.maker.*;
import io.bisq.core.trade.protocol.tasks.seller.SellerProcessFiatTransferStartedMessage;
import io.bisq.core.trade.protocol.tasks.seller_as_maker.SellerAsMakerBroadcastPayoutTx;
import io.bisq.core.trade.protocol.tasks.seller_as_maker.SellerAsMakerSignPayoutTx;
import io.bisq.core.trade.protocol.tasks.seller_as_maker.SellerAsMaker___ProcessPayoutTxFinalizedMessage;
import io.bisq.core.trade.protocol.tasks.seller_as_maker.SellerAsMaker___SendFinalizePayoutTxRequest;
import io.bisq.core.trade.protocol.tasks.seller_as_taker.SellerAsTakerCreatesDepositTxInputs;
import io.bisq.core.trade.protocol.tasks.seller_as_taker.SellerAsTakerSignAndPublishDepositTx;
import io.bisq.core.trade.protocol.tasks.taker.*;
import io.bisq.gui.common.view.FxmlView;
import io.bisq.gui.common.view.InitializableView;
import io.bisq.gui.components.TitledGroupBg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

import javax.inject.Inject;
import java.util.Arrays;

@FxmlView
public class DebugView extends InitializableView {


    @FXML
    TitledGroupBg titledGroupBg;
    @FXML
    Label label;
    @FXML
    ComboBox<Class> taskComboBox1, taskComboBox2;

    @Inject
    public DebugView() {
    }

    @Override
    public void initialize() {
        titledGroupBg.setText("Intercept task");
        label.setText("Select Task:");
        final ObservableList<Class> items1 = FXCollections.observableArrayList(Arrays.asList(
                        /*---- Protocol ----*/
                        OfferAvailabilityProtocol.class,
                        SendOfferAvailabilityRequest.class,
                        ProcessOfferAvailabilityResponse.class,
                        Boolean.class, /* used as separator*/

                        
                        /*---- Protocol ----*/
                        PlaceOfferProtocol.class,
                        ValidateOffer.class,
                        CreateOfferFeeTx.class,
                        AddOfferToRemoteOfferBook.class,
                        BroadcastCreateOfferFeeTx.class,
                        Boolean.class, /* used as separator*/

                        
                        /*---- Protocol ----*/
                        BuyerAsMakerProtocol.class,
                        MakerProcessPayDepositRequest.class,
                        MakerVerifyArbitrationSelection.class,
                        MakerVerifyTakerAccount.class,
                        MakerCreateAndSignContract.class,
                        BuyerAsMakerCreatesAndSignsDepositTx.class,
                        MakerLoadTakeOfferFeeTx.class,
                        MakerSetupDepositBalanceListener.class,
                        MakerSendPublishDepositTxRequest.class,

                        MakerProcessDepositTxPublishedMessage.class,

                        MakerVerifyTakerFeePayment.class,
                        BuyerSendFiatTransferStartedMessage.class,

                        BuyerAsTaker___ProcessFinalizePayoutTxRequest.class,
                        BuyerAsTakerSignAndFinalizePayoutTx.class,
                        BuyerAsTaker___SendPayoutTxFinalizedMessage.class,
                        SellerAsMakerBroadcastPayoutTx.class,
                        Boolean.class, /* used as separator*/
                        

                        /*---- Protocol ----*/
                        SellerAsTakerProtocol.class,
                        TakerSelectArbitrator.class,
                        TakerCreateTakerFeeTx.class,
                        TakerBroadcastTakerFeeTx.class,
                        SellerAsTakerCreatesDepositTxInputs.class,
                        TakerSendPayDepositRequest.class,

                        TakerProcessPublishDepositTxRequest.class,
                        TakerVerifyMakerAccount.class,
                        TakerVerifyAndSignContract.class,
                        SellerAsTakerSignAndPublishDepositTx.class,
                        TakerSendDepositTxPublishedMessage.class,

                        SellerProcessFiatTransferStartedMessage.class,

                        TakerVerifyMakerFeePayment.class,
                        SellerAsMakerSignPayoutTx.class,
                        SellerAsMaker___SendFinalizePayoutTxRequest.class,

                        SellerAsMaker___ProcessPayoutTxFinalizedMessage.class,
                        SellerAsMakerBroadcastPayoutTx.class,
                        Boolean.class /* used as separator*/
                )
        );
        final ObservableList<Class> items2 = FXCollections.observableArrayList(Arrays.asList(
                        /*---- Protocol ----*/
                        BuyerAsTakerProtocol.class,
                        TakerSelectArbitrator.class,
                        TakerCreateTakerFeeTx.class,
                        TakerBroadcastTakerFeeTx.class,
                        SellerAsTakerCreatesDepositTxInputs.class,
                        TakerSendPayDepositRequest.class,

                        TakerProcessPublishDepositTxRequest.class,
                        TakerVerifyMakerAccount.class,
                        TakerVerifyAndSignContract.class,
                        SellerAsTakerSignAndPublishDepositTx.class,
                        TakerSendDepositTxPublishedMessage.class,

                        TakerVerifyMakerFeePayment.class,
                        SellerAsMakerSignPayoutTx.class,
                        BuyerSendFiatTransferStartedMessage.class,

                        BuyerAsTaker___ProcessFinalizePayoutTxRequest.class,
                        BuyerAsTakerSignAndFinalizePayoutTx.class,
                        BuyerAsTaker___SendPayoutTxFinalizedMessage.class,
                        SellerAsMakerBroadcastPayoutTx.class,
                        Boolean.class, /* used as separator*/
                        
                        
                         /*---- Protocol ----*/
                        SellerAsMakerProtocol.class,
                        MakerProcessPayDepositRequest.class,
                        MakerVerifyArbitrationSelection.class,
                        MakerVerifyTakerAccount.class,
                        MakerCreateAndSignContract.class,
                        BuyerAsMakerCreatesAndSignsDepositTx.class,
                        MakerSetupDepositBalanceListener.class,
                        MakerSendPublishDepositTxRequest.class,

                        MakerProcessDepositTxPublishedMessage.class,

                        SellerProcessFiatTransferStartedMessage.class,

                        MakerVerifyTakerFeePayment.class,
                        SellerAsMakerSignPayoutTx.class,
                        SellerAsMaker___SendFinalizePayoutTxRequest.class,

                        SellerAsMaker___ProcessPayoutTxFinalizedMessage.class,
                        SellerAsMakerBroadcastPayoutTx.class,
                        Boolean.class /* used as separator*/
                )
        );

        taskComboBox1.setVisibleRowCount(items1.size());
        taskComboBox1.setItems(items1);
        taskComboBox1.setConverter(new StringConverter<Class>() {
            @Override
            public String toString(Class item) {
                if (item.getSimpleName().contains("Protocol"))
                    return "--- " + item.getSimpleName() + " ---";
                else if (item.getSimpleName().contains("Boolean"))
                    return "";
                else
                    return item.getSimpleName();
            }

            @Override
            public Class fromString(String s) {
                return null;
            }
        });


        taskComboBox2.setVisibleRowCount(items2.size());
        taskComboBox2.setItems(items2);
        taskComboBox2.setConverter(new StringConverter<Class>() {
            @Override
            public String toString(Class item) {
                if (item.getSimpleName().contains("Protocol"))
                    return "--- " + item.getSimpleName() + " ---";
                else if (item.getSimpleName().contains("Boolean"))
                    return "";
                else
                    return item.getSimpleName();
            }

            @Override
            public Class fromString(String s) {
                return null;
            }
        });
    }

    @FXML
    void onSelectTask1() {
        Class item = taskComboBox1.getSelectionModel().getSelectedItem();
        if (!item.getSimpleName().contains("Protocol")) {
            Task.taskToIntercept = item;
        }
    }

    @FXML
    void onSelectTask2() {
        Class item = taskComboBox2.getSelectionModel().getSelectedItem();
        if (!item.getSimpleName().contains("Protocol")) {
            Task.taskToIntercept = item;
        }
    }


}
