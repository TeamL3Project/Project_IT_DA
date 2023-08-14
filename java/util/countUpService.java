package util;

import Channel.DB.ChannelBean;
import Content.DB.ContentBean;

import java.sql.*;

import static util.dbService.dbConntect;

public class countUpService {
	private static int result = 0;

	private static void dbUpdateProcess(String query) {
		try (Connection connect = dbService.ds.getConnection();) {
			Statement statement = connect.createStatement();
			result = statement.executeUpdate(query);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public static void contentVisitUp(ContentBean content) {
		dbConntect();
		String query = "update chboard set boardvisit = " + (content.getBoardVisit() + 1) + " where boardnum = " + content.getBoardNum();
		dbUpdateProcess(query);
	}

	public static void channelVisitDown(ChannelBean channel) {
		dbConntect();
		String query = "update channellist set chvisit = " + (channel.getChvisit() - 1) + " where chnum = " + channel.getChnum();
		dbUpdateProcess(query);
	}

	public static void ContentLikeUp(ContentBean content) {
		dbConntect();
		String query = "update chboard set boardheart = " + (content.getBoardHeart() + 1) + " where chnum = " + content.getBoardNum();
		dbUpdateProcess(query);
	}
	public static void ContentLikeDown(ContentBean content) {
		dbConntect();
		String query = "update chboard set boardheart = " + (content.getBoardHeart() - 1) + " where chnum = " + content.getBoardNum();
		dbUpdateProcess(query);
	}
}
