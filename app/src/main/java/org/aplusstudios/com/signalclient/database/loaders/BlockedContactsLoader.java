package org.aplusstudios.com.signalclient.database.loaders;

import android.content.Context;
import android.database.Cursor;

import org.aplusstudios.com.signalclient.database.DatabaseFactory;
import org.aplusstudios.com.signalclient.utils.AbstractCursorLoader;


public class BlockedContactsLoader extends AbstractCursorLoader {

  public BlockedContactsLoader(Context context) {
    super(context);
  }

  @Override
  public Cursor getCursor() {
    return DatabaseFactory.getRecipientDatabase(getContext())
                          .getBlocked();
  }

}
