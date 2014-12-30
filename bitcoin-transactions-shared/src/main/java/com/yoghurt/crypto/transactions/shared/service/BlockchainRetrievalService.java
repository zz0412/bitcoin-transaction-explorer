package com.yoghurt.crypto.transactions.shared.service;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.yoghurt.crypto.transactions.shared.domain.BlockInformation;
import com.yoghurt.crypto.transactions.shared.domain.TransactionInformation;
import com.yoghurt.crypto.transactions.shared.domain.exception.ApplicationException;

@RemoteServiceRelativePath("blockchain-retrieve")
public interface BlockchainRetrievalService extends RemoteService {
  String getRawTransactionHex(String txid) throws ApplicationException;

  TransactionInformation getTransactionInformation(String txid) throws ApplicationException;

  String getRawBlockHex(int height) throws ApplicationException;

  String getRawBlockHex(String blockHash) throws ApplicationException;

  String getLastRawBlockHex() throws ApplicationException;

  BlockInformation getBlockInformation(String blockHash) throws ApplicationException;

  String getLatestBlockHash();
}
