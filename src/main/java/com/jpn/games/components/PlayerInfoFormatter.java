package com.jpn.games.components;

import java.util.List;
import java.util.Map;

import com.jpn.games.domain.PlayerInfo;
import com.jpn.games.exceptions.GameException;

public interface PlayerInfoFormatter {

	public String formatPlayersInfo(List<PlayerInfo> players);
}
